/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import pojo.Concert;
import pojo.ConcertTicket;
import pojo.PaymentHistory;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.junit.Ignore;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.Users;
/**
 *
 * @author ACO
 */

@RunWith(MockitoJUnitRunner.class)
public class DAOConcertTest {
    

    @Mock
    private DAOConcert daoConcert; 

    
//    private int nextUserId = 1;
//    private int nextConcertId = 1;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
//    public boolean mockInsertUser(Users newUser) {
//        newUser.setId(nextUserId++); 
//        return true;
//    }
//    
//    public boolean mockInsertConcert(Concert newConcert) {
//        newConcert.setId(nextConcertId++);
//        return true;
//    }
//    
//    public boolean mockInsertConcertTicket(ConcertTicket newTicket) {
//        Integer mockTicketId = 1; 
//        newTicket.setId(mockTicketId++); 
//        return true; 
//    }

//  TEST GETBY METHOD
    //  Success
        @Test
        public void testGetBy_success() {
            System.out.println("Test GetBy - Success");
            
            Users user = new Users();
            user.setId(1);
            user.setUsername("testUser");
            user.setEmail("testUser@gmail.com");
            user.setPassword("password123");
//            mockInsertUser(user);

            ConcertTicket ticket = new ConcertTicket();
            ticket.setId(1);
            ticket.setUserId(user.getId());
//            mockInsertConcertTicket(ticket); 

            List<ConcertTicket> mockTicketList = new ArrayList<>();
            mockTicketList.add(ticket); 

            when(daoConcert.getBy(user.getId())).thenReturn(mockTicketList);

            List<ConcertTicket> result = daoConcert.getBy(user.getId());

            assertNotNull(result);
            assertTrue(result.size() > 0);
            assertEquals((Integer) result.get(0).getUserId(), (Integer) user.getId());
        }

    //  Error
        @Test
        public void testGetBy_Error() {
            System.out.println("Test GetBy - Error");
            Users user = new Users();
            user.setId(1); 
            user.setUsername("User");
            user.setEmail("User@gmail.com");
            user.setPassword("password123");

            when(daoConcert.getBy(user.getId())).thenReturn(new ArrayList<>());

            List<ConcertTicket> result = daoConcert.getBy(user.getId());

            assertTrue(result.isEmpty()); 
        }
//  END TEST GETBY
    
//  TEST RETRIEVE TABLE CONCERT
    //  Success
        @Test
        public void testRetrieveTblConcert_Success() {
            System.out.println("Test retrieveTblConcert - Success");

            Concert mockConcert = new Concert();
            mockConcert.setName("Mock Concert");
            mockConcert.setCreatedAt(new Date());

            List<Concert> mockConcertList = new ArrayList<>();
            mockConcertList.add(mockConcert);

            when(daoConcert.retrieveTblConcert()).thenReturn(mockConcertList);

            List<Concert> result = daoConcert.retrieveTblConcert();

            assertNotNull(result);
            assertTrue(result.size() > 0);
        }
    //  Error
        @Test
        public void testRetrieveTblConcert_Error() {
        System.out.println("Test retrieveTblConcert - Error");
        
        when(daoConcert.retrieveTblConcert()).thenReturn(new ArrayList<>());
        
        List<Concert> result = daoConcert.retrieveTblConcert();
        
        assertTrue(result.isEmpty());
    }
//  END TEST RETRIEVE TABLE CONCERT 
    
//  TEST GET CONCERT BY ID
    //  Success  
        @Test
        public void testGetConcertById_Success() {
            System.out.println("Test GetConcertById - Success");

            Concert concert = new Concert();
            concert.setId(1);
            concert.setName("Test Concert");
            concert.setCreatedAt(new Date());
//            mockInsertConcert(concert);

            when(daoConcert.getConcertById(concert.getId())).thenReturn(concert);

            Concert result = daoConcert.getConcertById(concert.getId());

            assertNotNull(result);
            assertEquals((Integer) result.getId(), (Integer) concert.getId());
        }

    //  Error  
        @Test
        public void testGetConcertById_Error() {
        System.out.println("Test GetConcertById - Error");

        Concert concert = new Concert();
        concert.setId(1);
        
        when(daoConcert.getConcertById(concert.getId())).thenReturn(null);
        
        Concert result = daoConcert.getConcertById(concert.getId());
        
        assertNull(result);
    }
//  END TEST GET CONCERT BY ID

//  TEST INSERT CONCERT TICKET
    //  Success
        @Test
        public void testInsert_Succes() {
            System.out.println("insertBooking - Success");

            ConcertTicket ticket = new ConcertTicket();
            ticket.setId(1);
            ticket.setUserId(1);
            ticket.setConcertId(1);
            ticket.setFullName("Testing");
            ticket.setPhoneNumber("098765432112");
            ticket.setIdCard("1234567890123456789");
            ticket.setCreatedAt(new Date());

            when(daoConcert.insert(ticket)).thenReturn(true);

            boolean result = daoConcert.insert(ticket);

            assertTrue(result);
        }
    
    //  Error
        @Test
        public void testInsert_Error() {
            System.out.println("insertBooking - Error");

            ConcertTicket ticket = new ConcertTicket();
            ticket.setId(1);
            ticket.setUserId(1);
            ticket.setConcertId(1);
            ticket.setFullName("Testing");
            ticket.setPhoneNumber("098765432112");
            ticket.setIdCard("1234567890123456789");
            ticket.setCreatedAt(new Date());

            when(daoConcert.insert(ticket)).thenReturn(false);

            boolean result = daoConcert.insert(ticket);

            assertFalse(result);
        }
//  END TEST INSERT CONCERT TICKET
        
//  TEST INSERT PAYMENT HISTORY
    //  Success
        @Test
        public void testInsertHistory_Success() {
            System.out.println("insertHistory - Success");

            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(1);
            history.setConcertName("Concert A");
            history.setBookingDate(new Date());

            when(daoConcert.insertHistory(history)).thenReturn(true);

            boolean result = daoConcert.insertHistory(history);

            assertTrue(result);
        }
        
    //  Error
        @Test
        public void testInsertHistory_Error() {
            System.out.println("insertHistory - Error");

            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(1);
            history.setConcertName("Concert A");
            history.setBookingDate(new Date());

            when(daoConcert.insertHistory(history)).thenReturn(false);

            boolean result = daoConcert.insertHistory(history);

            assertFalse(result);
        }
//  END TEST INSERT PAYMENT HISTORY

//  TEST GET PAYMENT BY USER ID
    //  Success
        @Test
        public void testGetHistoryByUserId_Success() {
            System.out.println("getHistoryByUserId - Success");
            
            Users user = new Users();
            user.setId(1);
            user.setUsername("testUser");
            user.setEmail("testUser@gmail.com");
            user.setPassword("password123");
            
            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(user.getId());

            List<PaymentHistory> ticketList = new ArrayList<>();
            ticketList.add(history); 

            when(daoConcert.getHistoryByUserId(history.getUserId())).thenReturn(ticketList);

            List<PaymentHistory> result = daoConcert.getHistoryByUserId(history.getUserId());

            assertNotNull(result);
            assertTrue(result.size() > 0);
            assertEquals((Integer) result.get(0).getUserId(), (Integer) user.getId());
        }
        
    //  Error
        @Test
        public void testGetHistoryByUserId_Error() {
            System.out.println("getHistoryByUserId - Error");
            
            Users user = new Users();
            user.setId(1);
            user.setUsername("testUser");
            user.setEmail("testUser@gmail.com");
            user.setPassword("password123");
            
            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(user.getId());

            when(daoConcert.getHistoryByUserId(history.getUserId())).thenReturn(new ArrayList<>());

            List<PaymentHistory> result = daoConcert.getHistoryByUserId(history.getUserId());
            
            assertTrue(result.size() == 0);
        }
//  END TEST GET PAYMENT BY USER ID

//  TEST DELETE BY PAYMENT HISTORY ID
    //  Success
        @Test
        public void testDeleteById_Success() {
            System.out.println("deleteById - Success");

            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(1);
            history.setConcertName("Test Concert");
            history.setBookingDate(new Date());

            when(daoConcert.deleteById(history.getId())).thenReturn(true);

            boolean result = daoConcert.deleteById(history.getId());

            assertTrue(result);
        }
        
    //  Error
        @Test
        public void testDeleteById_Error() {
            System.out.println("deleteById - Error");

            PaymentHistory history = new PaymentHistory();
            history.setId(1);
            history.setUserId(1);
            history.setConcertName("Test Concert");
            history.setBookingDate(new Date());

            when(daoConcert.deleteById(history.getId())).thenReturn(false);

            boolean result = daoConcert.deleteById(history.getId());

            assertFalse(result);
        }
}
