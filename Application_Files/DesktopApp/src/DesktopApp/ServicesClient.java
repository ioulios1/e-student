
package DesktopApp;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

/**
 * Η κλάση ServicesClient αποτελεί μια βοηθητικού τύπου κλάση κλήσης του webService
 * "services". 
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class ServicesClient {
private WebTarget webTarget;
    private Client client;
    
    
    
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";

    public ServicesClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("services");
    }
    
    /**
     * Μέσω της μεθόδου getServices καλείται το αντίστοιχο WebService όπου ελέγχονται
     * τα επιτρεπτά Services για τον εκάστωτε ρόλο του χρήστη. Με τον τρόπο αυτό επιτυγχάνεται
     * ο δυναμικός έλεγχος και μορφοποίηση των επιτρεπτών υπηρεσιών που μπορεί να εκτελέσει ο 
     * κάθε χρήστης ξεχωριστά. Η κάθε αλλαγή στη βάση αναφορικά με τους χρήστες , τα roles,
     * και τα services αναγνωρίζεται αυτοματοποιημένα.
     * @param username : Το όνομα χρήστη που λειτουργεί την desktop εφαρμογή
     * @param role : Ο ρόλος του χρήστη όπως αναγνωρίστηκε απο τη βάση (Admin ή Secretary)
     * @return
     * @throws ClientErrorException 
     */
    public String getServicies(String username,String role) throws ClientErrorException {
        WebTarget resource = webTarget;
           
        //δημιουργία των παραμετρων
        if (username != null)  {
            resource = resource.queryParam("n1", username);
            resource = resource.queryParam("n2", role);
        }
       
        //τύπος που γυρνάει το webservice - δέχεται ο client
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class);


    }
    
    public void close() {
        client.close();
    }
    
}
