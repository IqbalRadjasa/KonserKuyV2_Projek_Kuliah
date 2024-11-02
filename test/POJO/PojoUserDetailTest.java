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
import pojo.UserDetail;

/**
 *
 * @author ACO
 */
public class PojoUserDetailTest {
    
    @Test
    public void testGetterSetterUserDetail(){
        UserDetail instance = new UserDetail();
        
//      Getter Setter Id
        instance.setId(1);
        assertTrue(1 == instance.getId());
        
//      Getter Setter Username
        instance.setUserId(1);
        assertTrue(1 == instance.getUserId());
        
//      Getter Setter Email
        instance.setAddress("Alamat");
        assertTrue("Alamat" == instance.getAddress());
        
//      Getter Setter Phone Number
        instance.setPhoneNumber("098765432112");
        assertTrue("098765432112" == instance.getPhoneNumber());
        
//      Getter Setter Substrict
        instance.setSubdistrict("Kecamatan");
        assertTrue("Kecamatan" == instance.getSubdistrict());
        
//      Getter Setter Ward
        instance.setWard("Kelurahan");
        assertTrue("Kelurahan" == instance.getWard());
        
//      Getter Setter Updated At
        instance.setUpdatedAt(new Date());
        assertEquals(new Date(), instance.getUpdatedAt());
    }
            
    @Test
    public void testUserDetailrConstructor() {
        int userId = 1;
        String address = "Alamat";
        String phoneNumber = "098765432112";
        String substrict = "Kecamatan";
        String ward = "Kelurahan";
        Date updatedAt = new Date();

        UserDetail ud = new UserDetail(userId, address, phoneNumber, substrict, ward, updatedAt);

        assertTrue(userId == ud.getUserId());
        assertEquals(address, ud.getAddress());
        assertEquals(phoneNumber, ud.getPhoneNumber());
        assertEquals(substrict, ud.getSubdistrict());
        assertEquals(ward, ud.getWard());
        assertEquals(updatedAt, ud.getUpdatedAt());
    }
    
    @Test
    public void testToString(){
        UserDetail instance = new UserDetail();
        
        assertNotNull(instance.toString());
    }
}
