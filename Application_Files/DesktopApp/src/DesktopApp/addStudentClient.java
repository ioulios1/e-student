
package DesktopApp;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.*;
/**
 * Η κλάση addStudentClient εκτελεί τις βοηθητικές λειτουργίες για την κλήση
 * του webService add_student.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class addStudentClient {
    private WebTarget webTarget;
    private Client client;
    
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";

    public addStudentClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("add_student");
        
    }
    /**
     * Περνά παραμετρικά τα δεδομένα στο αντιστοιχο webService
     * @param username : Το όνομα του χρήστη που εκτελεί την προσθήκη
     * @param arg1 : Τα δεδομένα προς αποθήκευση (στοιχεία φοιτητή)
     * @param key : Το μοναδικό κλειδί αυθεντικοποίησης που αποδόθηκε κατα το login 
     * @return ένα μήνυμα επιτυχίας ή αποτυχίας της διαδικασίας
     * @throws ClientErrorException 
     */
    public String putUser(String username,String arg1, String key) throws ClientErrorException {
            
            WebTarget resource = webTarget;
                
            resource = resource.queryParam("usr", username);
            resource = resource.queryParam("key", key);
            resource = resource.queryParam("info", arg1);
        
            Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
            return build.get(String.class);
    }
    

    public void close() {
        client.close();
    }
}
