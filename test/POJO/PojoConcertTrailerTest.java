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
import pojo.ConcertTrailer;
import pojo.Concert;

/**
 *
 * @author ACO
 */
public class PojoConcertTrailerTest {

//  TEST GETTER SETTER CONCERT
    //  Success      
    @Test
    public void testGetterSetterConcertTrailer_Success(){
        System.out.println("Getter Setter Concert Trailer - Success");
        
        ConcertTrailer ct = new ConcertTrailer();
        
//      Getter Setter Id
        ct.setId(1);
        assertTrue(1 == ct.getId());
        
//      Getter Setter Image
        ct.setTrailer("video.mp4");
        assertTrue("video.mp4" == ct.getTrailer());
        
//      Getter Setter Created At
        ct.setCreatedAt(new Date());
        assertEquals(new Date(), ct.getCreatedAt());
        
//      Getter Setter Updated At
        ct.setUpdatedAt(new Date());
        assertEquals(new Date(), ct.getUpdatedAt());
        
//      Getter Setter Concert
        List<Concert> concerts = new ArrayList<>();
        ct.setConcert(concerts);
        assertEquals(concerts, ct.getConcert());
    }
    //  Error      
    @Test
    public void testGetterSetterConcertTrailer_Error(){
        System.out.println("Getter Setter Concert Trailer - Error");
        
        ConcertTrailer ct = new ConcertTrailer();
        
//      Getter Setter Id
        ct.setId(1);
        assertFalse(2 == ct.getId());
        
//      Getter Setter Image
        ct.setTrailer("video.mp4");
        assertFalse("wrong_video.mp4" == ct.getTrailer());
        
//      Getter Setter Created At
        ct.setCreatedAt(new Date());
        assertEquals(new Date(), ct.getCreatedAt());
        
//      Getter Setter Updated At
        ct.setUpdatedAt(new Date());
        Date futureDate = new Date(System.currentTimeMillis() + 100000);
        assertNotEquals(futureDate, ct.getUpdatedAt());
        
//      Getter Setter Concert
        List<Concert> incorrectConcerts = new ArrayList<>();
        incorrectConcerts.add(new Concert());
        
        List<Concert> concerts = new ArrayList<>();
        ct.setConcert(concerts);
        assertNotEquals(incorrectConcerts, ct.getConcert());
    }
//  END TEST GETTER SETTER CONCERT

//  TEST CONSTRUCTOR CONCERT
    //  Success    
    @Test
    public void testConcertTrailerConstructor_Success() {
        System.out.println("Constructor Concert Trailer - Success");
        
        Date createdAt = new Date();
        Date updatedAt = new Date();

        // Create an instance using the constructor
        ConcertTrailer cv = new ConcertTrailer("trailer.mp4", createdAt, updatedAt);

        // Assert that the constructor correctly assigns the values
        assertEquals("trailer.mp4", cv.getTrailer());
        assertEquals(createdAt, cv.getCreatedAt());
        assertEquals(updatedAt, cv.getUpdatedAt());
    }
    //  Error    
    @Test
    public void testConcertTrailerConstructor_Error() {
        System.out.println("Constructor Concert Trailer - Error");
        
        Date createdAt = new Date();
        Date updatedAt = new Date();

        ConcertTrailer cv = new ConcertTrailer("trailer.mp4", createdAt, updatedAt);

        assertNotEquals("wrong_trailer.mp4", cv.getTrailer());
        assertNotEquals(new Date(createdAt.getTime() + 10000), cv.getCreatedAt());
        assertNotEquals(new Date(updatedAt.getTime() + 10000), cv.getUpdatedAt());
    }
//  END TEST CONSTRUCTOR CONCERT
}
