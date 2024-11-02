/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOConcert;
import DAO.DAOUser;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import pojo.PaymentHistory;
import pojo.Users;
/**
 *
 * @author ACO
 */
public class PaymentHistoryServletTest {
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private PaymentHistoryServlet servlet;
    
    // Mock the PaymentHistory class
    private DAOConcert mockDaoConcert;
    private PaymentHistory paymentHistory;
    
    @Before
    public void setUp() {
        mockDaoConcert = mock(DAOConcert.class); 
//        servlet = new PaymentHistoryServlet(mockDaoConcert); 
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        
        paymentHistory = new PaymentHistory();
        paymentHistory.setId(1);
        paymentHistory.setUserId(1);
        paymentHistory.setConcertName("Concert One");
        LocalDateTime now = LocalDateTime.now();
        paymentHistory.setBookingDate(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
    
    }

    /**
     * Test of getServletInfo method, of class PaymentHistoryServlet.
     */

    @Test
    public void testDoGet_UserLoggedIn_ReturnsPaymentHistoryInJSON() throws Exception {
        Users user = new Users();
        user.setId(1);
        
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        // Mock the insert method to save the payment history
        when(mockDaoConcert.insertHistory(any(PaymentHistory.class))).thenReturn(true);

        paymentHistory.insert(); // Assuming you want to call insert() on the instance

        // Prepare mock return value for getByUserId
        List<PaymentHistory> historyList = new ArrayList<>();
        historyList.add(paymentHistory);

        // Set up the mocked method to return the list by userId
        when(mockDaoConcert.getHistoryByUserId(paymentHistory.getId())).thenReturn(historyList); 

        // Prepare to capture the response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Invoke the method to be tested
        servlet.doGet(request, response);

        // Assert
        writer.flush();
        String responseOutput = stringWriter.toString().trim();
        assertNotNull(responseOutput);
//        // Optionally, verify the response format
//        assertTrue(responseOutput.startsWith("["));
//        assertTrue(responseOutput.endsWith("]"));
   }
    
}
