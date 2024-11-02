/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.ConcertTicket;
import pojo.Users;
import pojo.PaymentHistory;

/**
 *
 * @author ACO
 */
@WebServlet(name = "PaymentHistoryServlet", urlPatterns = {"/PaymentHistoryServlet"})
public class PaymentHistoryServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");

        if (user != null) {
            int userId = user.getId();

            PaymentHistory history = new PaymentHistory();
            history.setUserId(userId);

            List<PaymentHistory> historyResult = history.getByUserId(); // Fetch all PaymentHistory

            response.setContentType("application/json;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                // Create a StringBuilder to construct the JSON response
                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("[");

                for (int i = 0; i < historyResult.size(); i++) {
                    PaymentHistory ph = historyResult.get(i);
                    jsonBuilder.append("{");
                    jsonBuilder.append("\"id\":").append(ph.getId()).append(",");
                    jsonBuilder.append("\"userId\":").append(ph.getUserId()).append(",");
                    jsonBuilder.append("\"bookingDate\":\"").append(ph.getBookingDate()).append("\",");
                    jsonBuilder.append("\"concertName\":\"").append(ph.getConcertName()).append("\"");
                    jsonBuilder.append("}");

                    // Add a comma if it's not the last element
                    if (i < historyResult.size() - 1) {
                        jsonBuilder.append(",");
                    }
                }
                jsonBuilder.append("]");

                // Print the JSON response
                out.print(jsonBuilder.toString());
                response.setStatus(HttpServletResponse.SC_OK); // Set status to OK
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"User not logged in\"}");
        }
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
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");

        if (user != null) {
            String historyId = request.getParameter("historyId");

            if (historyId != null && !historyId.isEmpty()) {
                // Assuming PaymentHistory has a delete method
                PaymentHistory history = new PaymentHistory();
                boolean deleted = history.deleteById(Integer.parseInt(historyId));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (deleted) {
                    response.getWriter().write("{\"status\":\"success\", \"message\":\"History deleted successfully\"}");
                } else {
                    response.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to delete history\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Missing or invalid history ID\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"User not logged in\"}");
        }
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
