/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApp;

import java.io.UnsupportedEncodingException;
import static java.net.URLEncoder.encode;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONObject;

/**
 *
 * @author ioulios
 */
public class RestCaller {
    
    private static final String BASE_URI = "http://localhost:8080/WebServices/webresources";
    private Client client=javax.ws.rs.client.ClientBuilder.newClient();
    private WebTarget webTarget= client.target(BASE_URI);
    private JSONObject myJson;
   
    
    public String login(String n1,String n2)
    {
        WebTarget resource = webTarget.path("login");
        
        resource = resource.queryParam("n1", n1);

        resource = resource.queryParam("n2", n2);
        
        resource = resource.queryParam("n3", "1");
        
        
        
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class);
      
    }
    
     public String getRole(String n1)
    {
        WebTarget resource = webTarget.path("roles");
        
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class);
      
    }
    
    public JSONObject getUserInfo(String usr,String key)
    {
         WebTarget resource = webTarget.path("getInfo");
         
        resource = resource.queryParam("usr", usr);
        
        resource = resource.queryParam("key", key);
        
        resource = resource.queryParam("info",(String) null);
        
        
        
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        //JSONObject json=new JSONObject(build.get(String.class));
        
        return new JSONObject(build.get(String.class));
        
        
    }
    
    public JSONObject getLesson(String lessID,String usr,String key)
    {
         WebTarget resource = webTarget.path("getLesson");
         
        
        resource = resource.queryParam("usr", usr);
        
        resource = resource.queryParam("key", key);
        
        resource = resource.queryParam("info", lessID);
        
        
        
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        //JSONObject json=new JSONObject(build.get(String.class));
        
        return new JSONObject(build.get(String.class));
        
        
    }
    
    public JSONObject getLessons(String usr,String key)
    {
         WebTarget resource = webTarget.path("getLessons");
         
        resource = resource.queryParam("usr", usr);
         
        resource = resource.queryParam("key", key);
        
        resource = resource.queryParam("info","0");        
        System.out.println("uri test -----> "+usr+"  "+key);
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        //JSONObject json=new JSONObject(build.get(String.class));
        System.out.println("get lessons return test ----> "+build.get(String.class));
        return new JSONObject(build.get(String.class));
        
        
    }
    
    
    public int logout(String usr,String key)
    {
         WebTarget resource = webTarget.path("logout");
         
        resource = resource.queryParam("n1", usr);
        
        resource = resource.queryParam("key", key);
        
        
        
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        //JSONObject json=new JSONObject(build.get(String.class));
        
        return build.get(Integer.class);
        
        
    }
    
    public String putClasses(String[] c,String usr,String key) throws UnsupportedEncodingException
    {
        
        
        JsonBuilderFactory factory=Json.createBuilderFactory(null);

        JsonArrayBuilder jsonA = factory.createArrayBuilder();
      
        for(String s:c)
        {
            System.out.println(s);
            jsonA= jsonA.add(factory.createObjectBuilder()
                            .add("lessid", s));
        }
        
        JsonArray jA=jsonA.build();
        String safeuri=encode(jA.toString(),"UTF-8");
        
        System.out.println("jsonArray string test"+jA.toString());
        System.out.println("jsonArray string test"+safeuri);
        WebTarget resource = webTarget.path("putclasses");
         
        resource = resource.queryParam("usr", usr);
        
        resource = resource.queryParam("key", key);
        
        resource = resource.queryParam("classes", safeuri);
        
        
        
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        
        
        return build.get(String.class);
        
        
    }
    
}
