/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import servlet.UserServlet;
import pojo.Users;
import pojo.UserDetail;
import DAO.DAOUser;


/**
 *
 * @author ACO
 */
public class UserServletTest {
    
    private UserServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DAOUser daoUser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new UserServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        
        daoUser = mock(DAOUser.class);
    }

    /**
     * Test of getServletInfo method, of class UserServlet.
     */
    @Test
    public void testDoGet_SuccessfulRetrieval() throws Exception {
        // Arrange: Set up the mock session and user
        Users user = new Users();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("testpassword");

        List<UserDetail> userDetails = new ArrayList<>();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(1);
        userDetail.setAddress("123 Test St");
        userDetail.setPhoneNumber("1234567890");
        userDetail.setSubdistrict("Test District");
        userDetail.setWard("Test Ward");
        userDetails.add(userDetail);

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(daoUser.getByUserId(user.getId())).thenReturn(userDetails);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        servlet.doGet(request, response);

        writer.flush();
        String output = stringWriter.toString();
    }
    
    @Test
    public void testDoGet_NoSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        String output = stringWriter.toString();
    }
    
    @Test
    public void testDoGet_NoUserDetails() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("testpassword");

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(daoUser.getByUserId(user.getId())).thenReturn(new ArrayList<>());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        servlet.doGet(request, response);

        writer.flush();
        String output = stringWriter.toString();
    }
    
    @Test
    public void testDoPost_UpdateExistingUser() throws Exception {
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("address")).thenReturn("456 New Address");
        when(request.getParameter("phoneNumber")).thenReturn("0987654321");
        when(request.getParameter("subdistrict")).thenReturn("New Subdistrict");
        when(request.getParameter("ward")).thenReturn("New Ward");

        List<UserDetail> existingUserDetails = new ArrayList<>();
        UserDetail existingUserDetail = new UserDetail();
        existingUserDetail.setUserId(1);
        existingUserDetails.add(existingUserDetail);

        when(daoUser.getByUserId(1)).thenReturn(existingUserDetails);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        writer.flush();
        String output = stringWriter.toString();
    }
    
    @Test
    public void testDoPost_InsertNewUser() throws Exception {
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("address")).thenReturn("789 Another Address");
        when(request.getParameter("phoneNumber")).thenReturn("1234567890");
        when(request.getParameter("subdistrict")).thenReturn("Another Subdistrict");
        when(request.getParameter("ward")).thenReturn("Another Ward");

        when(daoUser.getByUserId(2)).thenReturn(new ArrayList<>());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        writer.flush();
        String output = stringWriter.toString();
    }
}
