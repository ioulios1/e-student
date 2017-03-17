package com.axicik;


import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */

@Path("add_user")
public class AddUserResource {
    
    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.

    
    private final int seviceNum=4; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.
    
   
    public AddUserResource() {
    }
    
    

    /**
     * Προσθέτει έναν νέο χρήστη(Διαχειριστή η Γραμματέα) στην βάση δεδομένων. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Json Κώδικας με τα στοιχεία του νέου χρήστη 
     * Μορφή json:
     * { "student": 
     *      { "name": Ονομα χρήστη, 
     *        "last_name": Επίθετο χρήστη,
     *        "phone": αριθμός τηλεφόνου,
     *        "username": Αριθμό μητρόου,
     *        "password": Κωδικός, 
     *        "role": κωδικός ρόλου
     *      }
     * }
     * @return ένα String που περιέχει ένα μήνυμα που αφορά την επιτυχία ή όχι του web service.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Consumes("text/plain")
    public String getName(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) throws SQLException, ClassNotFoundException {
       
            DBManager mdbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            
            User u=mdbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη  
            
            if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
                if(u.hasService(seviceNum)) // Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                    return mdbm.addUser(info); //Μετατρέπει το string σε json και προσθέτει τον νέο χρήστη στην βάση.
                 else
                    return "An error has occured: Permission Denied";
            else
                return "An error has occured: User not added";
        
    }
}
