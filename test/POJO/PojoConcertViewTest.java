/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import pojo.ConcertView;
import pojo.Concert;

/**
 *
 * @author ACO
 */
public class PojoConcertViewTest {

//  TEST GETTER SETTER CONCERT VIEW
    //  Success
        @Ignore
        public void testGetterSetterConcertView_Success(){
            System.out.println("Test Getter Setter Concert View - Success");

            ConcertView cv = new ConcertView();

    //      Getter Setter Id
            cv.setId(1);
            assertTrue(1 == cv.getId());

    //      Getter Setter Image
            cv.setImage("image-1.jpg");
            assertTrue("image-1.jpg" == cv.getImage());

    //      Getter Setter Created At
            cv.setCreatedAt(new Date());
            assertEquals(new Date(), cv.getCreatedAt());

    //      Getter Setter Updated At
            cv.setUpdatedAt(new Date());
            assertEquals(new Date(), cv.getUpdatedAt());

    //      Getter Setter Concert
            List<Concert> concerts = new ArrayList<>();
            cv.setConcert(concerts);
            assertEquals(concerts, cv.getConcert());
        }
    //  Error
        @Ignore
        public void testGetterSetterConcertView_Error(){
        System.out.println("Test Getter Setter Concert View - Error");
        
        ConcertView cv = new ConcertView();
        
//      Getter Setter Id
        cv.setId(1);
        assertFalse(2 == cv.getId());
        
//      Getter Setter Image
        cv.setImage("image-1.jpg");
        assertFalse("wrongimage-1.jpg" == cv.getImage());
        
//      Getter Setter Created At
        cv.setCreatedAt(new Date());
        Date futureDate = new Date(System.currentTimeMillis() + 100000);
        assertNotEquals(futureDate, cv.getCreatedAt());
        
//      Getter Setter Updated At
        cv.setUpdatedAt(new Date());
        assertNotEquals(futureDate, cv.getUpdatedAt());
        
//      Getter Setter Concert
        List<Concert> incorrectConcerts = new ArrayList<>();
        incorrectConcerts.add(new Concert());
        
        List<Concert> concerts = new ArrayList<>();
        cv.setConcert(concerts);
        assertNotEquals(incorrectConcerts, cv.getConcert());
    }
//  END TEST GETTER SETTER CONCERT VIEW

//  TEST CONSTRUCTOR CONCERT VIEW
    //  Success
        @Test
        public void testConcertViewConstructor_Success() {
            System.out.println("Test Constructor - Success");

            Date createdAt = new Date();
            Date updatedAt = new Date();

            // Create an instance using the constructor
            ConcertView cv = new ConcertView("image-2.jpg", createdAt, updatedAt);

            // Assert that the constructor correctly assigns the values
            assertEquals("image-2.jpg", cv.getImage());
            assertEquals(createdAt, cv.getCreatedAt());
            assertEquals(updatedAt, cv.getUpdatedAt());
        }
    //  Error
        @Test
        public void testConcertViewConstructor_Error() {
        System.out.println("Test Constructor - Error");
        
        Date createdAt = new Date();
        Date updatedAt = new Date();

        ConcertView cv = new ConcertView("image-2.jpg", createdAt, updatedAt);

        assertNotEquals("wrongimage-2.jpg", cv.getImage());
        assertNotEquals(new Date(createdAt.getTime() + 10000), cv.getCreatedAt());
        assertNotEquals(new Date(updatedAt.getTime() + 10000), cv.getUpdatedAt());
    }
//  END TEST CONSTRUCTOR CONCERT VIEW
}
