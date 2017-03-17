package com.axicik;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
@Path("getLessons")
public class GetLessonsResource {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.

    private final int serviceNum=7; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.
    
    /**
     * Creates a new instance of GetLessonsResource
     */
    public GetLessonsResource() {
    }

    /**
     * Επιστρέφει όλα τα μαθήματα ή μόνο τα μαθήματα ενός φοιτητή. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Όνομα φοιτητή προς αναζήτηση ή "0" για όλα τα μαθήματα. 
     * @return ένα String που περιέχει τα μαθήματα σε μορφη json array. Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά.
     * Μορφή json :
     * { "lessons": 
     *      [{ "name": Ονομα μαθήματος, 
     *        "lessid": κωδικός μαθήματος},
     *        ... ,
     *       { "name": Ονομα μαθήματος, 
     *        "lessid": κωδικός μαθήματος}
     *      ]
     * }
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLessons(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) throws SQLException, ClassNotFoundException {
       
            DBManager dbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            User u=dbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη .
           
            if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
                        
                if(u.hasService(serviceNum)) // Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                    return dbm.getLessons(info); //Επιστρέφει όλα τα μαθήματα ή μόνο τα μαθήματα φοιτητή του φοιτητή "info".
                else
                    return "An error has occured: Permission Denied";
            else
                return "403";
       
    }

}
