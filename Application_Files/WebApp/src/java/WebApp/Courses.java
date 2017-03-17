
package WebApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
@WebServlet(name = "Courses", urlPatterns = {"/Courses"})
public class Courses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RestCaller rc=new RestCaller();
        HttpSession ses = request.getSession (false);
        
        String key=(String) ses.getAttribute("key");
        String usr=(String) ses.getAttribute("usr");
        
        JSONObject lessonJson=rc.getLessons(usr,key);
        JSONArray lessonsArray=lessonJson.getJSONArray("lessons");
        
        
        String lessName,lessID;
        
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Servlet Curces</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"container3\">");
            out.println("<div id=\"Songslist\">");
            out.println("a/a    |id |name<br>");
            
            out.println("<form name=\"CourcesForm\" action=\"/WebApp/HomePage\" method=\"post\">");
                            
            for (int i=0;i<lessonsArray.length();i++)
            {
                lessName=lessonsArray.getJSONObject(i).getString("name");
                lessID=lessonsArray.getJSONObject(i).getString("lessid");
                out.print ("<input type=\"checkbox\" name=\"class\" value=\""+lessID+"\"> ");
                out.print (""+i+" <a href=\"localhost:8080/WebApp/Course?"+lessID+"\">"+lessID+" "+lessName+"</a><br>");
                out.print ("<br>");
            }
            out.println("<p><label class=\"field\"></label><input type=\"submit\" value=\"Submit\"></p>");  
                        out.println("</form>");
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
