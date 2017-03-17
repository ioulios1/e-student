/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.*;

/**
 * Αρχική σελίδα, εκτελεί βασικές ενέργειες ελέγχου και εκτύπωσης του html κώδικα
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */

@WebServlet (name = "HomePage", urlPatterns =
{
    "/HomePage"
})
public class HomePage extends HttpServlet
{
    
    
    private JSONObject myJson;
    String user;
    String pass;
    
    
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        RestCaller rc=new RestCaller();
        //δεδομενα απο login
        user=request.getParameter ("user");
        pass=request.getParameter ("pass");
        String key;
        response.setContentType ("text/html;charset=UTF-8");
       
        //αν υπαρχει το session το επιστρεφει αλλιως δεν δημιουργει τιποτα
        HttpSession ses = request.getSession (false);
        if(ses!=null)
        {
            
            if(ses.getAttribute("loged")==null)
            {
                //τσεκαρω αν αυτος που εκανε login είναι φοιτητης ή οχι
                if(rc.getRole(user).equals("Student"))
                {
                    
                    key=rc.login(user,pass);
                    //επιτυχημενο login
                    if (!key.equals("0"))
                    {
                        ses.setAttribute("key", key);
                        ses.setAttribute("loged", true);
                        ses.setAttribute("usr", user);
                    }
                    else
                    {
                        //den exeis kanei login
                        ses.setAttribute("loged", false);
                    }
                }
                else
               {
                   //δεν εχεις κανει login
                   ses.setAttribute("loged", false);
               }
                
            }
            
            
            
        
            

            try (PrintWriter out = response.getWriter ())
            {
                out.println ("<!DOCTYPE html>");
               
                out.println ("<html>");
                out.println ("<head>");
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
                                
                
                out.println ("<title>e-study</title>");            
                out.println ("</head>");
                out.println ("<body>");
               


                
                if ((boolean)ses.getAttribute("loged"))
                {

                    user=(String) ses.getAttribute("usr");
                    key=(String) ses.getAttribute("key");



                    //αυτο το block κωδικα αφορα τη δυνατοτητα του φοιτητη να
                    //να δηλωσει μαθηματα - λαμβανει τα επιλεγμένα checkbox
                    String[] classes=request.getParameterValues("class");
                    if(classes!=null)
                    {
                        String str = rc.putClasses(classes,user,key);
                    }
                    
                    
                       //για εμφανιση στοιχειων χρηστη
                        myJson=rc.getUserInfo(user, key);
                        JSONObject json=(JSONObject)myJson.get("PersonnalInfo");
                        
                       out.println("<div id=\"container3\">");
                       
                        
                        out.println("<div id=\"topContainer\">");
                        out.println("<div id=\"circular\"></div>");
                       out.println ("Γεια σου "+ json.getString("name") +" "+json.getString("last_name")+ "<br>");
                        
                       out.println ("====================<br>");
                        out.println ("AM : "+json.getString("registrationNumber")+"<br>");
                        out.println ("email : "+json.getString("email")+" phone : "+json.getString("phone")+"<br>");
                       
                       out.println("</div>");
                          
                        
                       
                        
        
        
   
                        
                        
                        out.println("<div id=\"Songslist\">");
                        JSONArray jsonA=myJson.getJSONArray("Grades");
                        out.println("<table style=\"border-width: 1px; padding-bottom: 3%; border-top-style: outset; border-bottom-style: inset; width: 80%; padding-top: 3%; padding-left: 5%; margin-top: 5%; background-color: whitesmoke; margin-left: 10%;\">");
                        out.println("<br><br>");
                         out.println ("<tr><td><b>A/A</td><td><b>Cources</td><td><b>Grade</td></tr>");
                        String nm;
                        String lid;
                        for(int i=0;i<jsonA.length();i++)
                        {
                            nm=jsonA.getJSONObject(i).getString("name");
                            lid=jsonA.getJSONObject(i).getString("lessid");
                            out.println("<tr>");
                            out.print ("<td>"+i+"</td>");
                            out.println("<td><a href=\"http://localhost:8080/WebApp/Course?"+lid+"\">"+nm+"</a></td>");
                            out.println ("<td>"+jsonA.getJSONObject(i).get("grade")+"</td>");
                            out.println("</tr>");
                        }
                                            
                        out.println("</table>");
                       out.println("<div id=\"Courses\">");
                        out.println("<form name=\"CourcesForm\" action=\"/WebApp/Courses\" method=\"post\">");
                            out.println("<p><label class=\"field\"></label><input type=\"submit\" value=\"Cources\"></p>");  
                        out.println("</form>");
                        out.println("</div>");
                        out.println("<div id=\"Logout\">");
                        out.println("<form name=\"LogoutForm\" action=\"/WebApp/LoginPage\" method=\"post\">");
                            out.println("<p><label class=\"field\"></label><input type=\"submit\" value=\"Logout\"></p>");  
                        out.println("</form>");

                        
                        
                        
                        
                        
                }else
                {
                    out.println ("Wrong password!!!<br>");
                    out.println ("Please Login  <a href=\"http://localhost:8080/WebApp/LoginPage\">here</a> <br>");
                    out.println ("====================<br>");
                }
                


               out.println("</div>");
               out.println("</div>");
               
               out.println ("</body>");
               out.println ("</html>");
               out.flush();
               out.close();
            }
        }else
        {
             try (PrintWriter out = response.getWriter ())
            {
                out.println ("<!DOCTYPE html>");
                out.println ("<html>");
                out.println ("<head>");
                out.println ("<title>e-study</title>");            
                out.println ("</head>");
                out.println ("<body>");
                out.println ("Please Login  "+"<a href=\"http://localhost:8080/WebApp/LoginPage\">here</a>"+"<br>");
                out.println ("====================<br>");
                out.println ("</body>");
                out.println ("</html>");
                out.flush();
                out.close();
            }
        }
        
   
    }
    
    
    
    
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest (request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest (request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

    

    
}


