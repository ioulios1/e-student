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
@Path("roles")
public class RolesResources {

    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
    
    /**
     * Creates a new instance of RolesResources
     */
    public RolesResources() {
    }

     /**
     * Επιστρέφει τον ρόλο του χρήστη.
     * @param a Όνομα Χρήστη 
     * @return String - ο ρόλος του χρήστη.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRole(@QueryParam ("n1") String a) {
        
        try {
            
            DBManager dm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
  
            return dm.getRole(a); //Επιστρέφει τον ρόλο του χρήστη.
            
        } catch (ClassNotFoundException ex) {
            return "-1";
        } catch (SQLException ex) {
            return "Error "+ex.getErrorCode();
        }
        
       
        
    }

    
}
