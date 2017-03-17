/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Path("getLesson")
public class GetLessonResource {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
    
    private final int serviceNum=7; // Σταθερά - Μοναδικός Κωδικός του κάθε web service.

    /**
     * Creates a new instance of GetLessonResource
     */
    public GetLessonResource() {
    }

    /**
     * Επιστρέφει τα στοιχεία ενός μαθήματος Ο χρήστης πρέπει να 
     * έχει τα απαραίτητα δικαιώματα και να έχει κάνει login.
     * 
     * @param username Όνομα Χρήστη 
     * @param key Κλείδι Χρηστη
     * @param info Κωδικός μαθήματος 
     * @return ένα String που περιέχει τα στοιχεία του μαθήματος σε μορφη json. Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά
     * Μορφή json :
     * { "lesson": 
     *      { "name": Ονομα μαθήματος, 
     *        "semester": αριθμός εξαμήνου,
     *        "lessid": κωδικός μαθήματος,
     *        "description": περιγραφή
     *      }
     * }
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLesson(@QueryParam ("usr") String username,@QueryParam("key") String key,@QueryParam ("info") String info) {
        try {
            DBManager dbm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            User u=dbm.checkKey(username,key); //Ελέγχει αν υπάρχει χρήστης και επιστρέφει ένα αντικείμενο User στοιχεία του χρήστη .
            
            if(u.getUsrExist()) // Αν ο χρήστης υπάρχει τότε επιστρέφει true αλλιώς false.
                if(u.hasService(serviceNum))// Αν ο χρήστης έχει δικαίωμα να καλεί το συγκεκριμένο web service τότε επιστρέφει true αλλιώς false.
                    return dbm.getLesson(info); //Επιστρέφει τα στοιχεία του μαθήματος με κωδικό μαθήματος "info"
                else
                    return "An error has occured: Permission Denied";
            else
                return "403";
            
        } catch (ClassNotFoundException ex) {
            return "-1";
        } catch (SQLException ex) {
            return "-2";
        }
    }
}
