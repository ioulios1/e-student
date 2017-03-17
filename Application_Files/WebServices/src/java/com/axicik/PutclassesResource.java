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

@Path("putclasses")
public class PutclassesResource {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
    
    private final int serviceNum=5; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.
    
    /**
     * Creates a new instance of PutclassesResource
     */
    public PutclassesResource() {
    }

    /**
     * Προσθέτει νέα μαθήματα που επέλεξε ο φοιτητης στα μαθήματα του. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Json Κώδικας με τα μαθήματα.
     * Μορφή json:
     * [{ "lessonID": κωδικός μαθήματος }, ... , { "lessonID": κωδικός μαθήματος }]
     * 
     * @return ένα String που περιέχει ένα μήνυμα που αφορά την επιτυχία ή όχι του web service.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String putClasses(@QueryParam ("usr") String username,@QueryParam ("key") String key,@QueryParam ("classes") String jsonA) throws SQLException {
       try {
            DBManager mdbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            User u=mdbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη .
            if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
                if(u.hasService(serviceNum))// Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                {
                    mdbm.putClasses(jsonA,username); //Μετατρέπει το string σε json και προσθέτει τα μαθήματα στον χρήστη "username".

                    return "Classes Added Successfully";
                }
                else
                    return "An error has occured: Permission Denied";
             else
                return "An error has occured: Classes not added";
        } catch (ClassNotFoundException ex) {
            return "ClassNotFoundException";
        } 
    }

    
}
