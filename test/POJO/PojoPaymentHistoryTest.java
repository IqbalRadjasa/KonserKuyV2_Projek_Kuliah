/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import DAO.DAOConcert;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import pojo.PaymentHistory;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author ACO
 */
@RunWith(MockitoJUnitRunner.class)
public class PojoPaymentHistoryTest {
    
    private static int idCounter;
    
    @Mock
    private DAOConcert mockDaoConcert;

    private PaymentHistory paymentHistory;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        idCounter = 1;

        paymentHistory = new PaymentHistory();
    }

//  TEST GETTER SETTER PAYMENT HISTORY
//  Success
    @Test
    public void testGetterSetterPaymentHistory_Success(){
        System.out.println("Test Getter Setter Payment History - Success");
        
        PaymentHistory ph = new PaymentHistory();
        
//      Getter Setter Id
        ph.setId(1);
        assertTrue(1 == ph.getId());
        
//      Getter Setter User id
        ph.setUserId(1);
        assertTrue(1 == ph.getUserId());
        
//      Getter Setter Concert Name
        ph.setConcertName("Concert A");
        assertTrue("Concert A" == ph.getConcertName());
        
//      Getter Setter Booking Date
        ph.setBookingDate(new Date());
        assertEquals(new Date(), ph.getBookingDate());
    }
    
//  Error
    @Test
    public void testGetterSetterPaymentHistory_Error(){
        System.out.println("Test Getter Setter Payment History - Error");
        
        PaymentHistory ph = new PaymentHistory();
        
//      Getter Setter Id
        ph.setId(1);
        assertFalse(2 == ph.getId());
        
//      Getter Setter User id
        ph.setUserId(1);
        assertFalse(2 == ph.getUserId());
        
//      Getter Setter Concert Name
        ph.setConcertName("Concert A");
        assertFalse("Wrong Concert A" == ph.getConcertName());
        
//      Getter Setter Booking Date
        ph.setBookingDate(new Date());
        Date futureDate = new Date(System.currentTimeMillis() + 100000);
        assertNotEquals(futureDate, ph.getBookingDate());
    }
//  END TEST GETTER SETTER PAYMENT HISTORY

//  TEST INSERT PAYMENT HISTORY
//  Success
    @Test
    public void testInsertPaymentHistory_Success() {
        System.out.println("Test Insert Payment History - Success");
        PaymentHistory ph = new PaymentHistory();
        
        ph.setId(idCounter++);
        ph.setUserId(1);
        ph.setConcertName("Concert A");
        ph.setBookingDate(new Date());
        
        PaymentHistory result = ph.insert();
        assertNotNull(result);
        
        boolean delete_result = ph.deleteById(ph.getId());
        assertTrue(delete_result);
    }
//  END TEST INSERT PAYMENT HISTORY

    @Test
    public void testGetByUserId() {
        PaymentHistory ph = new PaymentHistory();
        ph.setId(idCounter++);
        ph.setUserId(1);
        ph.setConcertName("Concert A");
        ph.setBookingDate(new Date());
        
        PaymentHistory result_insert = ph.insert();
        assertNotNull(result_insert);
        
        List<PaymentHistory> result = ph.getByUserId();
        assertNotNull(result);
        
        boolean delete_result = ph.deleteById(ph.getId());
        assertTrue(delete_result);
    }
    
    @Test
    public void testDeleteById() {
        PaymentHistory ph = new PaymentHistory();
        ph.setId(idCounter++);
        ph.setUserId(1);
        ph.setConcertName("Concert A");
        ph.setBookingDate(new Date());
        
        PaymentHistory result = ph.insert();
        assertNotNull(result);
        
        boolean delete_result = ph.deleteById(ph.getId());
        assertTrue(delete_result);
    }
        
}
