package com.axicik;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.*;

/**
 * REST Web Service
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
@Path("login")
public class LoginResource {

    
    @Context
    private UriInfo context;// Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.


    /**
     * Creates a new instance of GenericResource
     */
    public LoginResource() {
    }

    /**
     * Συνδεση χρήστη και καταχώρηση κλειδίου ταυτοποίησης.
     * @param a Όνομα Χρήστη 
     * @param b Κλείδι Χρηστη
     * @return String - Μοναδικό κλειδί
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces("text/plain")
    public String getLoginKey(@QueryParam ("n1") String a, @QueryParam ("n2") String b,@QueryParam ("n3") String c) throws ClassNotFoundException, SQLException {
       
        DBManager db=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
        return db.login(a, b,c); // κάνει την σύνδεση του χρήστη.
    }

    
}
