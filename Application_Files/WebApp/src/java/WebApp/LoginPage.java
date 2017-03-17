/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApp;

import static java.awt.SystemColor.window;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Διαχειρίζεται τις ενέργειες της σελίδας login
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
@WebServlet(name = "LoginPage", urlPatterns = {"/LoginPage"})
public class LoginPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //κατι σαν τον constructor η ον κρεατε 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //νεο session με τιμη true δλδ αν δεν υπαρχει ηδη session δημιουργει νεο , αλλιως αν υπαρχει
        //σου επιστρεφει αυτο.
        HttpSession ses = request.getSession (true);
        
        
        
        //αν δε φτιαχτηκε,γίνεται αυτη τη στιγμη
        if(!ses.isNew())
        {
            
            new RestCaller().logout((String)ses.getAttribute("usr"), (String)ses.getAttribute("key"));
            ses.invalidate();
            ses = request.getSession (true);
        }
        
        
        //το  sesion γινεται invalid 5 δευτερολεπτα πριν σβηστεί το κλειδή απο την βάση
        ses.setMaxInactiveInterval(60*9+55);
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Login</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"container\">");

            out.println("<form name=\"LoginForm\" action=\"/WebApp/HomePage\" method=\"post\">");
            out.println("<p><label class=\"field\">Username: </label><input type=\"text\" name=\"user\"></p>");

            out.println("<p><label class=\"field\">Password: </label><input type=\"password\" name=\"pass\"></p>");

            out.println("<p><label class=\"field\"></label><input type=\"submit\" value=\"Login\"></p>");  
            out.println("</form>");


            out.println("<div id=\"lower\">");
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
