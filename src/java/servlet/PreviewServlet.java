/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOConcert;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import pojo.Users;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Concert;
import pojo.ConcertTicket;
import pojo.PaymentHistory;


/**
 *
 * @author ACO
 */
@WebServlet(name = "PreviewServlet", urlPatterns = {"/PreviewServlet"})
public class PreviewServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    DAOConcert daoConcert = new DAOConcert();
    String concertId = request.getParameter("concertId");
    Concert concert = daoConcert.getConcertById(Integer.parseInt(concertId)); // Fetch single concert directly

    HttpSession session = request.getSession(false);
    JSONObject jsonResponse = new JSONObject(); // Create a JSONObject to hold the response

    if (session != null && session.getAttribute("user") != null) {
        Users user = (Users) session.getAttribute("user");

        // Add user details to the JSON response
        jsonResponse.put("status", "success");
        jsonResponse.put("userId", user.getId());
        jsonResponse.put("username", user.getUsername());
        jsonResponse.put("email", user.getEmail());
        jsonResponse.put("password", user.getPassword());

        // Check if the concert is not null and construct the concert JSON object
        if (concert != null) {
            JSONObject concertJson = new JSONObject();
            concertJson.put("id", concert.getId());
            concertJson.put("name", concert.getName());
            concertJson.put("desc", concert.getDesc());
            concertJson.put("quantity", concert.getQuantity());
            concertJson.put("price", concert.getPrice());
            concertJson.put("dateOfConcert", concert.getDateOfConcert());
            concertJson.put("trailerId", concert.getConcertTrailer().getId());
            concertJson.put("viewConcertId", concert.getConcertView().getId());
            concertJson.put("createdAt", concert.getCreatedAt());
            concertJson.put("updatedAt", concert.getUpdatedAt());

            // Add concertView details
            JSONObject concertViewJson = new JSONObject();
            concertViewJson.put("id", concert.getConcertView().getId());
            concertViewJson.put("image", concert.getConcertView().getImage());
            concertJson.put("concertView", concertViewJson);

            // Add concertTrailer details
            JSONObject concertTrailerJson = new JSONObject();
            concertTrailerJson.put("id", concert.getConcertTrailer().getId());
            concertTrailerJson.put("trailer", concert.getConcertTrailer().getTrailer());
            concertJson.put("concertTrailer", concertTrailerJson);

            // Add the concert JSON object to the response
            jsonResponse.put("concert", concertJson);
        } else {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Concert not found");
        }

    } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        jsonResponse.put("status", "error");
        jsonResponse.put("message", "No user session found");
    }

    // Send the JSON response
    out.print(jsonResponse.toString());
    out.flush();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String concertId = request.getParameter("concertId");
        String concertName = request.getParameter("concertName");
        String fullname = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phoneNumber");
        String idCard = request.getParameter("idCard");
        
        ConcertTicket ticket = new ConcertTicket();
        ticket.setUserId(Integer.parseInt(userId));
        ticket.setConcertId(Integer.parseInt(concertId));
        ticket.setFullName(fullname);
        ticket.setPhoneNumber(phoneNumber);
        ticket.setIdCard(idCard);

        ConcertTicket ticketResult = ticket.insert();
        
        PaymentHistory history = new PaymentHistory();
        history.setUserId(Integer.parseInt(userId));
        history.setConcertName(concertName);
        
        PaymentHistory historyResult = history.insert();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        if (ticketResult != null) {
            out.write("{\"status\":\"success\", \"message\":\"Booking successful\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"status\":\"error\", \"message\":\"Invalid credentials\"}");
        }
        out.flush();
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
