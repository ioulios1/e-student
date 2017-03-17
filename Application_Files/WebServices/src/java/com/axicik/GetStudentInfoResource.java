package com.axicik;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
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
@Path("getInfo")
public class GetStudentInfoResource {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
    
    private final int serviceNum=3; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.
    private final int serviceNum2=6; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.

    /**
     * Creates a new instance of GetStudentInfoResource
     */
    public GetStudentInfoResource() {
    }

    /**
     * Επιστρέφει τα στοιχεία(προσωπικά και βαθμούς) ενός φοιτητη. Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη
     * @param key Κλείδι Χρηστη
     * @param info Αριθμό μητρώου φοιτητή προς αναζήτηση στοιχείων ή null;
     * @return ένα String που περιέχει τα στοιχεία σε μορφη json array. Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά.
     * Μορφή json :
     * { "PersonalInfo": 
     *      { "name": Ονομα χρήστη, 
     *        "last_name": Επίθετο χρήστη,
     *        "phone": αριθμός τηλεφόνου,
     *        "username":  Όνομα Χρήστη,
     *        "registrationNumber": Αριθμό μητρόου,
     *        "email": email,
     *        "role": κωδικός ρόλου
     *      },
     *   "lessons": 
     *      [{ "name": Ονομα μαθήματος, 
     *        "lessid": κωδικός μαθήματος,
     *        "grade": βαθμός},
     *        ... ,
     *       { "name": Ονομα μαθήματος, 
     *        "lessid": κωδικός μαθήματος,
     *        "grade": βαθμός}
     *      ]
     * }
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/plain")
    public String getUserInfo(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) throws SQLException, ClassNotFoundException {
        
        DBManager dbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντικείμενο τηs κλάσης DBManager.
        User u=dbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη .
        if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
            if(u.hasService(serviceNum)) // Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                return dbm.getStudInfo(info); //Επιστρέφει τα στοιχεία του φοιτητη "info".
            else if (u.hasService(serviceNum2)) // Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                return dbm.getStudInfo(username); //Επιστρέφει τα στοιχεία του χρήστη που καλεί το Web Service.    
            else
                return "An error has occured: Permission Denied";
        else
            return "403";
       
        
    }

}
