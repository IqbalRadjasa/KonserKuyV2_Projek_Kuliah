/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pojo.Users;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import static org.mockito.Mockito.*;
import pojo.KonserKuyUtil;
import DAO.DAOLogin;

public class loginServletTest {

    private DAOLogin daoLogin;
    private loginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Users user;

    @Before
    public void setUp() {
        servlet = new loginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        // Set up a sample user
        user = new Users();
        user.setId(1);
        user.setUsername("iqbal");
        user.setPassword("iqbal123");
        user.setEmail("iqbal@gmail.com");
    }

    @Test
    public void testDoPost_SuccessfulLogin() throws Exception {
    //  Insert the data first      
        user.insert();
        
        when(request.getParameter("username")).thenReturn("iqbal");
        when(request.getParameter("password")).thenReturn("iqbal123");

        Users spyUser = Mockito.spy(user);
        when(spyUser.validasiLogin()).thenReturn(user);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        when(request.getSession()).thenReturn(session);

        Users result = user.validasiLogin();
        
        servlet.doPost(request, response); 
        verify(session).setAttribute(eq("user"), argThat(argumentUser ->
            argumentUser instanceof Users &&
            ((Users) argumentUser).getId() == user.getId() &&
            ((Users) argumentUser).getUsername().equals(user.getUsername()) &&
            ((Users) argumentUser).getPassword().equals(user.getPassword()) &&
            ((Users) argumentUser).getEmail().equals(user.getEmail())
        ));
        
        writer.flush();
        String output = stringWriter.toString();
        
        assertNotNull(result);
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
        
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();

            Users userToDelete = (Users) session.get(Users.class, result.getId());
            assertNotNull(userToDelete);

            session.delete(userToDelete);

            session.getTransaction().commit();
        } catch (Exception e) {
            fail("Deletion failed: " + e.getMessage());
        }
    }

    @Ignore
    public void testDoPost_UnsuccessfulLogin() throws Exception {
        user = new Users();
        user.setId(1);
        user.setUsername("dummy");
        user.setPassword("dummy123");
        user.setEmail("dummy@gmail.com");
        
        when(request.getParameter("username")).thenReturn(user.getUsername());
        when(request.getParameter("password")).thenReturn(user.getPassword());

        when(daoLogin.getBy(user.getUsername(), user.getPassword())).thenReturn(new ArrayList<>()); // Return an empty list

            
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

//        writer.flush();
//        String output = stringWriter.toString();
//
//        // Assert that the output indicates an unsuccessful login
//        assertTrue(output.contains("\"status\":\"failure\""));
//        assertTrue(output.contains("\"message\":\"Invalid username or password\""));
    }
}
