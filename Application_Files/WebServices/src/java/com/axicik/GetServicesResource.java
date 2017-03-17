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

@Path("services")
public class GetServicesResource {

    
    @Context
    private UriInfo context; // Δημιουργείτε μόνο του απο το Net-beans, όταν δημιουργείτε η κλάση και δεν το χρησιμοποιούμε κάπου.
   
    /**
     * Creates a new instance of RolesResources
     */
    public GetServicesResource() {
    }

    /**
     * Επιστρέφει τις υπηρεσίες που έχει δiκαίωμα να καλέσει ο χρήστης.
     * @param a Όνομα Χρήστη 
     * @param b Ρόλος Χρήστη
     * @return String - οι υπηρεσίες που έχει δικαίωμα να καλέσει ο χρήστης.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServices(@QueryParam ("n1") String a,@QueryParam ("n2") String b) {
        
        try {
            
            DBManager dm=DBManager.getInstance(); // Πέρνει το μοναδικό αντεικήμενο τηs κλάσης DBManager.
            return dm.getServices(a,b); //Επιστρέφει τις υπηρεσίες που έχει δiκαίωμα να καλέσει ο χρήστης.
            
        } catch (ClassNotFoundException ex) {
            return "-1";
        } catch (SQLException ex) {
            return "Error "+ex.getErrorCode();
        }
        
       
        
    }

    
}

