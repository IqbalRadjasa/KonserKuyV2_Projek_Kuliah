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
import org.junit.After;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author ACO
 */
public class RegisterServletTest {
    private DAOLogin daoLogin;
    private RegisterServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Users user;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        // Set up a sample user
        user = new Users();
        user.setId(1);
    }

    /**
     * Test of getServletInfo method, of class RegisterServlet.
     */

    @Test
    public void testDoPost_SuccessfulRegistration() throws Exception {
        // Arrange: set up request parameters for a new user
        when(request.getParameter("username")).thenReturn("iqbal");
        when(request.getParameter("email")).thenReturn("iqbal@gmail.com");
        when(request.getParameter("password")).thenReturn("iqbal123");

        // Prepare to capture the response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Act: call the doPost method
        servlet.doPost(request, response);

        // Assert: verify the output
        writer.flush();
        String output = stringWriter.toString();
    }

    @After
    public void tearDown() {
        // Cleanup: delete the test user if it was created
        Transaction trans = null;
        Session session = null;
        try {
            session = KonserKuyUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            // Fetch and delete the user
            Users userToDelete = (Users) session.get(Users.class, user.getId()); // Assuming user has an ID after insert
            if (userToDelete != null) {
                session.delete(userToDelete);
            }

            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback(); // Rollback if anything goes wrong
            }
            fail("Deletion failed: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close(); // Close the session
            }
        }
    }
    
}
