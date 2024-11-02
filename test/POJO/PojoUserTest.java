/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import DAO.DAOLogin;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.KonserKuyUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import pojo.Users;

/**
 *
 * @author ACO
 */
public class PojoUserTest {
    
    private Users user;
    private DAOLogin uDao;

    public PojoUserTest() {
    }
    
    @Before
    public void setUp() {
        user = new Users();
        user.setUsername("iqbal");
        user.setEmail("testing@gmail.com");
        user.setPassword("iqbal123");
        uDao = new DAOLogin();
    }
    
    @After
    public void tearDown() {
        user = null;
        uDao = null;
    }

    @Test
    public void testGetterSetterUser(){
        Users instance = new Users();
        
//      Getter Setter Id
        instance.setId(1);
        assertTrue(1 == instance.getId());
        
//      Getter Setter Username
        instance.setUsername("iqbal");
        assertTrue("iqbal" == instance.getUsername());
        
//      Getter Setter Email
        instance.setEmail("iqbal@gmail.com");
        assertTrue("iqbal@gmail.com" == instance.getEmail());
        
//      Getter Setter Password
        instance.setPassword("iqbal123");
        assertTrue("iqbal123" == instance.getPassword());
        
//      Getter Setter Address
        instance.setAddress("Alamat");
        assertTrue("Alamat" == instance.getAddress());
        
//      Getter Setter Phone Number
        instance.setPhoneNumber("098765432112");
        assertTrue("098765432112" == instance.getPhoneNumber());
        
//      Getter Setter Substrict
        instance.setSubstrict("Kecamatan");
        assertTrue("Kecamatan" == instance.getSubstrict());
        
//      Getter Setter Ward
        instance.setWard("Kelurahan");
        assertTrue("Kelurahan" == instance.getWard());
        
//      Getter Setter Created At
        instance.setCreatedAt(new Date());
        assertEquals(new Date(), instance.getCreatedAt());
        
//      Getter Setter Updated At
        instance.setUpdatedAt(new Date());
        assertEquals(new Date(), instance.getUpdatedAt());
    }
    
    @Test
    public void testUserConstructor(){
        String username = "iqbal";
        String email = "iqbal@gmail.com";
        String password = "iqbal123";
        String address = "Alamat";
        String phoneNumber = "098765432112";
        String substrict = "Kecamatan";
        String ward = "Kelurahan";
        Date createdAt = new Date();
        Date updatedAt = new Date();
        
        Users u = new Users(username, email, password, address, phoneNumber, substrict, ward, createdAt, updatedAt);
        
        assertEquals(username, u.getUsername());
        assertEquals(email, u.getEmail());
        assertEquals(password, u.getPassword());
        assertEquals(address, u.getAddress());
        assertEquals(phoneNumber, u.getPhoneNumber());
        assertEquals(substrict, u.getSubstrict());
        assertEquals(ward, u.getWard());
        assertEquals(createdAt, u.getCreatedAt());
        assertEquals(updatedAt, u.getUpdatedAt());
    }
    
    @Test
    public void testValidasiLogin() {
        Users result_insert = user.insert();
        assertNotNull(result_insert);

        Users result = user.validasiLogin();
        assertNotNull(result);

        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();

            Users userToDelete = (Users) session.get(Users.class, result_insert.getId());
            assertNotNull(userToDelete);

            session.delete(userToDelete);

            session.getTransaction().commit();
        } catch (Exception e) {
            fail("Deletion failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testInsert() {
        Users result = user.insert();

        assertNotNull(result);
        
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();

            Users userToDelete = (Users) session.get(Users.class, result.getId());
            assertNotNull(userToDelete);

            session.delete(userToDelete);

            session.getTransaction().commit();
        } catch (Exception e) {
            fail("Deletion failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testToString(){
        Users instance = new Users();
        
        assertNotNull(instance.toString());
    }
}
