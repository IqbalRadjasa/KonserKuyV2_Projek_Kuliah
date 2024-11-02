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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import servlet.EventServlet;
import DAO.DAOConcert;
import java.util.Date;
import pojo.Concert;
import pojo.ConcertView;

/**
 *
 * @author ACO
 */
public class EventServletTest {
    private EventServlet servlet;

    @Mock
    private DAOConcert daoConcert;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new EventServlet();
    }

    /**
     * Test of getServletInfo method, of class EventServlet.
     */
    @Test
    public void testDoGet_ReturnsConcertData() throws Exception {
        // Arrange: Set up mock concert data
        ConcertView concertView = new ConcertView();
        concertView.setImage("image_url.jpg");

        Concert concert1 = new Concert();
        concert1.setId(1);
        concert1.setName("Concert One");
        concert1.setDesc("Description for concert one");
        concert1.setDateOfConcert(new Date());
        concert1.setConcertView(concertView);

        Concert concert2 = new Concert();
        concert2.setId(2);
        concert2.setName("Concert Two");
        concert2.setDesc("Description for concert two");
        concert2.setDateOfConcert(new Date());
        concert2.setConcertView(null); 

        List<Concert> concertList = Arrays.asList(concert1, concert2);
        when(daoConcert.retrieveTblConcert()).thenReturn(concertList);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        String responseOutput = stringWriter.toString().trim();

        assertNotNull(responseOutput);
    }
    
}
