/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import pojo.Concert;
import pojo.ConcertView;
import pojo.ConcertTrailer;

/**
 *
 * @author ACO
 */
public class PojoConcertTest {
    
//  TEST GETTER SETTER CONCERT
    //  Success  
        @Test
        public void testGetterSetterConcert_Success(){
            System.out.println("Getter Setter Concert - Success");
        Concert instance = new Concert();
        
//      Getter Setter Id
        instance.setId(1);
        assertTrue(1 == instance.getId());
        
//      Getter Setter Name
        instance.setName("Concert A");
        assertTrue("Concert A" == instance.getName());
        
//      Getter Setter Deskripsi
        instance.setDesc("Deskripsi");
        assertTrue("Deskripsi" == instance.getDesc());
        
//      Getter Setter Quantity
        instance.setQuantity(10);
        assertTrue(10 == instance.getQuantity());
        
//      Getter Setter Price
        instance.setPrice(100);
        assertTrue(100 == instance.getPrice());
        
//      Getter Setter Date of Concert
        instance.setDateOfConcert(new Date());
        assertEquals(new Date(), instance.getDateOfConcert());
        
//      Getter Setter View Concert Id
        instance.setViewConcertId(1);
        assertTrue(1 == instance.getViewConcertId());
        
//      Getter Setter View Concert Id
        instance.setTrailerId(1);
        assertTrue(1 == instance.getTrailerId());
        
//      Getter Setter Created At
        instance.setCreatedAt(new Date());
        assertEquals(new Date(), instance.getCreatedAt());
        
//      Getter Setter Updated At
        instance.setUpdatedAt(new Date());
        assertEquals(new Date(), instance.getUpdatedAt());
        
//      Getter Setter ConcertView
        ConcertView cv = new ConcertView();
        cv.setId(1);
        
        instance.setConcertView(cv);
        assertEquals(cv, instance.getConcertView());
        
//      Getter Setter ConcertTrailer
        ConcertTrailer ct = new ConcertTrailer();
        ct.setId(1);
        
        instance.setConcertTrailer(ct);
        assertEquals(ct, instance.getConcertTrailer());
    }
    //  Error      
        @Test
        public void testGetterSetterConcert_Error() {
            System.out.println("Getter Setter Concert - Error");
        Concert instance = new Concert();

        instance.setId(1);
        assertFalse(2 == instance.getId());

        instance.setName("Concert A");
        assertFalse("Concert B".equals(instance.getName()));

        instance.setDesc("Deskripsi");
        assertFalse("Incorrect Description".equals(instance.getDesc()));

        instance.setQuantity(10);
        assertFalse(5 == instance.getQuantity());

        instance.setPrice(100);
        assertFalse(200 == instance.getPrice());

        Date originalDate = new Date();
        instance.setDateOfConcert(originalDate);
        Date laterDate = new Date(originalDate.getTime() + 1000);
        assertNotEquals(laterDate, instance.getDateOfConcert());

        ConcertView cv = new ConcertView();
        cv.setId(1);
        instance.setConcertView(cv);
        ConcertView cvDifferent = new ConcertView();
        cvDifferent.setId(2);
        assertNotEquals(cvDifferent, instance.getConcertView());

        ConcertTrailer ct = new ConcertTrailer();
        ct.setId(1);
        instance.setConcertTrailer(ct);
        ConcertTrailer ctDifferent = new ConcertTrailer();
        ctDifferent.setId(2);
        assertNotEquals(ctDifferent, instance.getConcertTrailer());

        instance.setName(null);
        assertNull(instance.getName());
    }
//  END TEST GETTER SETTER CONCERT

//  TEST CONSTRUCTOR CONCERT
    //  Success
        @Test
        public void testConcertConstructor_Success() {
        System.out.println("Constructor Concert - Success");
        
        String name = "Rock Concert";
        String desc = "A great rock concert.";
        int quantity = 100;
        int price = 50;
        Date dateOfConcert = new Date();
        int viewConcertId = 1;
        int trailerId = 2;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        
        ConcertView cv = new ConcertView("image-2.jpg", createdAt, updatedAt);
        ConcertTrailer ct = new ConcertTrailer("trailer.mp4", createdAt, updatedAt);

        Concert concert = new Concert(name, desc, quantity, price, dateOfConcert, viewConcertId, trailerId, createdAt, updatedAt, cv, ct);
        assertEquals(name, concert.getName());
        assertEquals(desc, concert.getDesc());
        assertEquals(quantity, concert.getQuantity());
        assertEquals(price, concert.getPrice());
        assertEquals(dateOfConcert, concert.getDateOfConcert());
        assertEquals(viewConcertId, concert.getViewConcertId());
        assertEquals(trailerId, concert.getTrailerId());
        assertEquals(createdAt, concert.getCreatedAt());
        assertEquals(updatedAt, concert.getUpdatedAt());
        assertEquals(cv, concert.getConcertView());
        assertEquals(ct, concert.getConcertTrailer());
    }
    //  Error
        @Test
        public void testConcertConstructor_Error() {
            System.out.println("Constructor Concert - Error");
            
            String name = "Rock Concert";
            String desc = "A great rock concert.";
            int quantity = 100;
            int price = 50;
            Date dateOfConcert = new Date();
            int viewConcertId = 1;
            int trailerId = 2;
            Date createdAt = new Date();
            Date updatedAt = new Date();

            ConcertView cv = new ConcertView("image-2.jpg", createdAt, updatedAt);
            ConcertTrailer ct = new ConcertTrailer("trailer.mp4", createdAt, updatedAt);

            Concert concert = new Concert(name, desc, quantity, price, dateOfConcert, viewConcertId, trailerId, createdAt, updatedAt, cv, ct);

            assertNotEquals("Incorrect Name", concert.getName());
            assertNotEquals("Incorrect Description", concert.getDesc());
            assertNotEquals(50, concert.getQuantity());
            assertNotEquals(1000, concert.getPrice());
            assertNotEquals(new Date(dateOfConcert.getTime() + 10000), concert.getDateOfConcert());
            assertNotEquals(2, concert.getViewConcertId());
            assertNotEquals(3, concert.getTrailerId());
            assertNotEquals(new Date(createdAt.getTime() + 10000), concert.getCreatedAt());
            assertNotEquals(new Date(updatedAt.getTime() + 10000), concert.getUpdatedAt());
            assertNotEquals(new ConcertView("different-image.jpg", createdAt, updatedAt), concert.getConcertView());
            assertNotEquals(new ConcertTrailer("different-trailer.mp4", createdAt, updatedAt), concert.getConcertTrailer());
        }
//  END TEST CONSTRUCTOR CONCERT
}
