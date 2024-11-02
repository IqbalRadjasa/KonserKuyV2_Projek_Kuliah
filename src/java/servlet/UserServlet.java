/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.UserDetail;
import pojo.Users;

/**
 *
 * @author ACO
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

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

        // Get session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            Users user = (Users) session.getAttribute("user");

            // Get userId from session
            int userId = user.getId();

            // Fetch UserDetail data from the database using DAOUser
            DAOUser daoUser = new DAOUser();
            List<UserDetail> userDetails = daoUser.getByUserId(userId);

            if (!userDetails.isEmpty()) {
                UserDetail userDetail = userDetails.get(0);

                out.write("{\"status\":\"success\","
                        + "\"userId\":\"" + userDetail.getUserId() + "\","
                        + "\"username\":\"" + user.getUsername() + "\","
                        + "\"email\":\"" + user.getEmail() + "\","
                        + "\"password\":\"" + user.getPassword() + "\","
                        + "\"address\":\"" + userDetail.getAddress() + "\","
                        + "\"phoneNumber\":\"" + userDetail.getPhoneNumber() + "\","
                        + "\"substrict\":\"" + userDetail.getSubdistrict() + "\","
                        + "\"ward\":\"" + userDetail.getWard() + "\"}");
            } else {
                out.write("{\"status\":\"success\","
                    + "\"userId\":\"" + user.getId()+ "\","
                    + "\"username\":\"" + user.getUsername() + "\","
                    + "\"email\":\"" + user.getEmail() + "\","
                    + "\"password\":\"" + user.getPassword() + "\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"status\":\"error\", \"message\":\"No user session found\"}");
        }

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String subdistrict = request.getParameter("subdistrict");
        String ward = request.getParameter("ward");

        DAOUser daoUser = new DAOUser();
        List<UserDetail> existingUserDetails = daoUser.getByUserId(userId);

        if (!existingUserDetails.isEmpty()) {
            UserDetail userDetail = existingUserDetails.get(0); 
            userDetail.setAddress(address);
            userDetail.setPhoneNumber(phoneNumber);
            userDetail.setSubdistrict(subdistrict);
            userDetail.setWard(ward);

            daoUser.updateUser(userDetail); 
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("User updated successfully");
        } else {
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(userId); 
            userDetail.setAddress(address);
            userDetail.setPhoneNumber(phoneNumber);
            userDetail.setSubdistrict(subdistrict);
            userDetail.setWard(ward);

            daoUser.insertUser(userDetail);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("User updated successfully");
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
