
package DesktopApp;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

/**
 * Η κλάση roleClient εκτελεί τις βοηθητικές λειτουργίες για την κλήση
 * του webService roles.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class roleClient {
    
    private WebTarget webTarget;
    private Client client;
    
    
    
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";

    public roleClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("roles");
    }
    /**
     * Καλείται το web service roles
     * @param username
     * @return
     * @throws ClientErrorException 
     */
    public String getRole(String username) throws ClientErrorException {
        WebTarget resource = webTarget;
           
        
        if (username != null)  {
            resource = resource.queryParam("n1", username);
            
        }
       
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class);
    }
    

    public void close() {
        client.close();
    }
    
}
