package com.axicik;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
@Path("add_student")
public class AddStudentResource {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου

    private final int serviceNum=0; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.
    
    /**
     * Creates a new instance of AddStudentResource
     */
    public AddStudentResource() {
    }

    /**
     * Προσθέτει έναν νέο φοιτητή στην βάση δεδομένων. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Json Κώδικας με τα στοιχεία του φοιτητή
     * μορφή json:
     * { "student": 
     *      { "name": Ονομα Φοιτητή, 
     *        "last_name": Επίθετο Φοιτητή,
     *        "phone": "αριθμός τηλεφόνου",
     *        "username": "Αριθμό μητρόου",
     *        "password": "Κωδικός" 
     *      }
     * }
     * @return ένα String που περιέχει ένα μήνυμα που αφορά την επιτυχία ή όχι του web service.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces("text/plain")
    public String putUser(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) throws ClassNotFoundException, SQLException {
        
        DBManager dbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
        
        User u=dbm.checkKey(username,key); // Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη 
        
        if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
            
            if(u.hasService(serviceNum))// Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                return dbm.addStudent(info); // Μετατρέπει το string σε json και προσθέτει το ν φοιτητή στην βάση.
            else
                return "An error has occured: Permission Denied";
        else
            return "An error has occured: You have to login";
     
    }
    
       
    
}
