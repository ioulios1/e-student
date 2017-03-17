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
@Path("logout")
public class LogoutResource {

   
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LogoutResource
     */
    public LogoutResource() {
    }

    /**
     * Αποσύνδεση χρήστη και διαγραφή κλειδίου ταυτοποίησης.
     * @param a Όνομα Χρήστη 
     * @param b Κλείδι Χρηστη
     * @return String - 1 επιτυχια -1 ClassNotFoundException -2 SQLException 
     */
    @GET
    @Produces("text/plain")
    public String logout(@QueryParam ("n1") String a, @QueryParam ("key") String key) {
        
        try {
            DBManager dbm=DBManager.getInstance();// Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            dbm.logout(a, key); // κάνει την σύνδεση του χρήστη.
            return "1";
        } catch (ClassNotFoundException ex) {
            return "-1";
        } catch (SQLException ex) {
            return "-2";
        }
        
       
        
    }
}
