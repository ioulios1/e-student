package com.axicik;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ioulios
 */
@Path("modifyFromFile")
public class ModifyGrades {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
    
    private final int serviceNum=1; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.

    /**
     * Creates a new instance of ModifyFromFileResource
     */
    public ModifyGrades() {
    }

    /**
     * Προσθέτει η αλλάζει τον βαθμό σε έναν ή περισότερουw φοιτητές. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Json Κώδικας με τα στοιχεία του νέου χρήστη 
     * Μορφή json:
     * { "lessonID": κωδικός μαθήματος,
     *   "Grades": 
     *      [{ "am": Αριθμός μητρώου , 
     *        "grade": βαθμός},
     *        ... ,
     *       { "am": Αριθμός μητρώου, 
     *        "grade": βαθμός}
     *      ]
     * }
     * @return ένα String που περιέχει ένα μήνυμα που αφορά την επιτυχία ή όχι του web service.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String putGradesFromFile(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) throws SQLException, ClassNotFoundException {
       
        DBManager dbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
        User u=dbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη .
            
        if(u.getUsrExist())// Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
            if(u.hasService(serviceNum)) // Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                return dbm.putGrades(info); //Μετατρέπει το string σε json και προσθέτει τους νέους βαθμούς στην βάση.
            else
                return "An error has occured: Permission Denied";
        else
            return "An error has occured: User not added";

    }

   
}
