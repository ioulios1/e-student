

package DesktopApp;


import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;


/**
 * Η κλάση loginClient εκτελεί τις βοηθητικές λειτουργίες για την κλήση
 * του webService login.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class loginClient {
    private WebTarget webTarget;
    private Client client;
        
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";

    public loginClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("login");
    }
    /**
     * Περνά παραμετρικά τα δεδομένα στο αντιστοιχο webService
     * @param username 
     * @param password 
     * @return ένα response απο το server όπου περιέχεται το key
     * @throws ClientErrorException 
     */
    public String getLoginKey(String username, String password) throws ClientErrorException {
        
        WebTarget resource = webTarget;
            //δημιουργία των παραμετρων   
            if ((username != null) && (password!=null)) {
                resource = resource.queryParam("n1", username);
                resource = resource.queryParam("n2", password);
                resource = resource.queryParam("n3", "2");
            }
        //τύπος που γυρνάει το webservice - δέχεται ο client    
        Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class);
    }
    

    public void close() {
        client.close();
    }
    
    
}
