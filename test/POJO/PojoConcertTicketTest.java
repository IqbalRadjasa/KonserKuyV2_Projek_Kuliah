/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import DAO.DAOConcert;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import pojo.ConcertTicket;


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
public class PojoConcertTicketTest {

    @Mock
    private DAOConcert daoConcert; 
    
    @InjectMocks
    private ConcertTicket concertTicket; // This will be the class under test
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
//  TEST GETTER SETTER CONCERT TICKET
    //  Success  
    @Ignore
    public void testGetterSetterConcertTicket_Success(){
        System.out.println("Getter Setter Concert Ticket - Success");
        
        ConcertTicket cti = new ConcertTicket();
        
//      Getter Setter Id
        cti.setId(1);
        assertTrue(1 == cti.getId());
        
//      Getter Setter User Id
        cti.setUserId(1);
        assertTrue(1 == cti.getUserId());
        
//      Getter Setter Concert Id
        cti.setConcertId(1);
        assertTrue(1 == cti.getConcertId());
        
//      Getter Setter Fullname
        cti.setFullName("iqbal");
        assertTrue("iqbal" == cti.getFullName());
        
//      Getter Setter Phone Number
        cti.setPhoneNumber("098765432112");
        assertTrue("098765432112" == cti.getPhoneNumber());
        
//      Getter Setter Id Card
        cti.setIdCard("098765432112");
        assertTrue("098765432112" == cti.getIdCard());
        
//      Getter Setter Id Card
        cti.setIdCard("098765432112");
        assertTrue("098765432112" == cti.getIdCard());
        
//      Getter Setter Created At
        cti.setCreatedAt(new Date());
        assertEquals(new Date(), cti.getCreatedAt());
    }
    //  Error    
    @Ignore
    public void testGetterSetterConcertTicket_Error() {
        System.out.println("Getter Setter Concert Ticket - Error");

        ConcertTicket cti = new ConcertTicket();

        cti.setId(1);
        assertFalse(2 == cti.getId());

        cti.setUserId(1);
        assertFalse(2 == cti.getUserId());

        cti.setConcertId(1);
        assertFalse(2 == cti.getConcertId());

        cti.setFullName("iqbal");
        assertFalse("differentName".equals(cti.getFullName()));

        cti.setPhoneNumber("098765432112");
        assertFalse("0123456789".equals(cti.getPhoneNumber()));

        cti.setIdCard("098765432112");
        assertFalse("differentIdCard".equals(cti.getIdCard()));

        cti.setCreatedAt(new Date());
        Date futureDate = new Date(System.currentTimeMillis() + 100000);
        assertNotEquals(futureDate, cti.getCreatedAt());
    }
//  END TEST GETTER SETTER CONCERT TICKET
    
    @Test
    public void testInsertConcertTicket() {
        ConcertTicket cti = new ConcertTicket(1, 1, "iqbal", "098765432112", "098765432112", new Date());
        
        ConcertTicket result = cti.insert();
        assertNotNull(result);
        
        Transaction trans = null;
        Session session = pojo.KonserKuyUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();

            ConcertTicket userToDelete = (ConcertTicket) session.get(ConcertTicket.class, result.getId());
            assertNotNull(userToDelete);

            session.delete(userToDelete);

            session.getTransaction().commit();
        } catch (Exception e) {
            fail("Deletion failed: " + e.getMessage());
        }
    }
}
