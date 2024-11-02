/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogoutServletTest {

    private LogoutServlet logoutServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logoutServlet = new LogoutServlet();
    }

    @Test
    public void testDoGet_LogoutSuccess() throws Exception {
        when(request.getSession(false)).thenReturn(session);

        logoutServlet.doGet(request, response);

        verify(session).invalidate();

        verify(response).sendRedirect("index.xhtml");
    }

    @Test
    public void testDoGet_NoSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        logoutServlet.doGet(request, response);

        verify(response).sendRedirect("index.xhtml");
    }
}

