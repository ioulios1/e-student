/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesktopApp;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

/**
 * Η κλάση  showStudentClient παρέχει τα βασικά βήματα για την κλήση του webservice
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class showStudentClient {
    private WebTarget webTarget;
    private Client client;
     
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";

    public showStudentClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("getInfo");
        
    }
    
    public String searchUser(String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        
            resource = resource.queryParam("usr", User.getUsername());
            resource = resource.queryParam("key", User.getKey());
            //ως info δίνεται ένα username. αναλόγως με το αν είναι σωστό ή όχι 
            //θα επιστραφεί κατάλληλο μήνυμα
            resource = resource.queryParam("info", username);
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class);
    }
    

    public void close() {
        client.close();
    }
}
