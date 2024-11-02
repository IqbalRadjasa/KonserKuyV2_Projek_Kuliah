/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import pojo.UserDetail;
import pojo.Users;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.ConcertTicket;
/**
 *
 * @author ACO
 */

@RunWith(MockitoJUnitRunner.class)
public class DAOUserTest {
    
    @Mock
    private DAOUser daoUser; 
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
//  TEST GET USER BY ID
    //  Success
        @Test
        public void testGetUserById_Success() {
        System.out.println("getUserById - Success");
        
        Users user = new Users();
        user.setId(1);
        user.setUsername("iqbal");
        
        when(daoUser.getUserById(user.getId())).thenReturn(user);
        
        Users result = daoUser.getUserById(user.getId());
        
        assertNotNull(result);
        assertEquals(result.getId(), user.getId());
    }
    
    //  Error
        @Test
        public void testGetUserById_Error() {
        System.out.println("getUserById - Error");
        
        Users user = new Users();
        user.setId(1);
        user.setUsername("iqbal");
        
        when(daoUser.getUserById(user.getId())).thenReturn(null);
        
        Users result = daoUser.getUserById(user.getId());
        
        assertNull(result);
    }
//  END TEST GET USER BY ID

//  TEST GET BY USER ID    
    //  Success
        @Test
        public void testGetByUserId_Success() {
            System.out.println("getByUserId - Success");

            Users user = new Users();
            user.setId(1);

            UserDetail detail = new UserDetail();
            detail.setId(1);
            detail.setUserId(user.getId());
            detail.setAddress("Address");
            detail.setSubdistrict("Subdistrict");
            detail.setWard("Ward");

            List<UserDetail> userList = new ArrayList<>();
            userList.add(detail); 

            when(daoUser.getByUserId(user.getId())).thenReturn(userList);

            List<UserDetail> result = daoUser.getByUserId(user.getId());

            assertNotNull(result);
            assertTrue(result.size() > 0);
            assertEquals(result.get(0).getUserId(), user.getId());
        }
        
    //  Error
        @Test
        public void testGetByUserId_Error() {
            System.out.println("getByUserId - Error");

            Users user = new Users();
            user.setId(1);

            UserDetail detail = new UserDetail();
            detail.setId(1);
            detail.setUserId(user.getId());
            detail.setAddress("Address");
            detail.setSubdistrict("Subdistrict");
            detail.setWard("Ward");

            when(daoUser.getByUserId(user.getId())).thenReturn(new ArrayList<>());

            List<UserDetail> result = daoUser.getByUserId(user.getId());

            assertTrue(result.isEmpty()); 
        }
//  END TEST GET BY USER ID

//  TEST INSERT USER DETAIL
    //  Success
        @Test
        public void testInsertUser_Success() {
            System.out.println("insertUserDetail - Success");

            Users user = new Users();
            user.setId(1);

            UserDetail userDetail = new UserDetail(); 
            userDetail.setId(1);
            userDetail.setUserId(user.getId());
            userDetail.setAddress("Alamat");
            userDetail.setPhoneNumber("098765432112");
            userDetail.setSubdistrict("Kecamatan");
            userDetail.setWard("Kelurahan");
            userDetail.setUpdatedAt(new Date());

            when(daoUser.insertUser(userDetail)).thenReturn(true);

            boolean result = daoUser.insertUser(userDetail);

            assertTrue(result);
        }
        
    //  Error
        @Test
        public void testInsertUser_Error() {
            System.out.println("insertUserDetail - Error");

            Users user = new Users();
            user.setId(1);

            UserDetail userDetail = new UserDetail(); 
            userDetail.setId(1);
            userDetail.setUserId(user.getId());
            userDetail.setAddress("Alamat");
            userDetail.setPhoneNumber("098765432112");
            userDetail.setSubdistrict("Kecamatan");
            userDetail.setWard("Kelurahan");
            userDetail.setUpdatedAt(new Date());

            when(daoUser.insertUser(userDetail)).thenReturn(false);

            boolean result = daoUser.insertUser(userDetail);

            assertFalse(result);
        }
//  END INSERT USER DETAIL

//  TEST UPDATE USER DETAIL
    //  Success
        @Test
        public void testUpdateUser_Success() {
        System.out.println("updateUser - Success");

        Users user = new Users();
        user.setId(1);

        String oldAddress = "Old Street";

        UserDetail userDetailUpdated = new UserDetail();
        userDetailUpdated.setId(1);
        userDetailUpdated.setUserId(user.getId());
        userDetailUpdated.setAddress(oldAddress);

        String newAddress = "Jalan Margonda";
        userDetailUpdated.setAddress(newAddress);

        when(daoUser.updateUser(userDetailUpdated)).thenReturn(true);

        boolean result = daoUser.updateUser(userDetailUpdated);
        
        assertTrue(result);
        assertEquals(newAddress, userDetailUpdated.getAddress());
        assertNotEquals(oldAddress, userDetailUpdated.getAddress());
    }
    
    //  Error
        @Test
        public void testUpdateUser_Error() {
        System.out.println("updateUser - Error");

        Users user = new Users();
        user.setId(1);

        String oldAddress = "Old Street";

        UserDetail userDetailUpdated = new UserDetail();
        userDetailUpdated.setId(1);
        userDetailUpdated.setUserId(user.getId());
        userDetailUpdated.setAddress(oldAddress);

        String newAddress = "Jalan Margonda";


        when(daoUser.updateUser(userDetailUpdated)).thenReturn(false);

        boolean result = daoUser.updateUser(userDetailUpdated);
        
        assertFalse(result);
        assertNotEquals(userDetailUpdated.getAddress(), newAddress);
        assertEquals(userDetailUpdated.getAddress(), oldAddress);
    }
//  END UPDATE USER DETAIL
}
