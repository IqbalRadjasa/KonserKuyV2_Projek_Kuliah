/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import pojo.Users;
import java.util.Date;
import org.junit.runner.RunWith;


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
public class DAOLoginTest {
    
    @Mock
    private DAOLogin daoLogin; 
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
//  TEST GET BY USERNAME AND PASSWORD
    //  Success
        @Ignore
        public void testGetBy_Success() {
            System.out.println("getBy - Success");

            Users user = new Users();
            user.setId(1);
            user.setUsername("iqbal");
            user.setPassword("iqbal123");

            List<Users> userList = new ArrayList<>();
            userList.add(user); 

            when(daoLogin.getBy(user.getUsername(), user.getPassword())).thenReturn(userList);

            List<Users> result = daoLogin.getBy(user.getUsername(), user.getPassword());

            assertNotNull(result);
            assertTrue(result.size() > 0);
            assertEquals(result.get(0).getUsername(), user.getUsername());
            assertEquals(result.get(0).getPassword(), user.getPassword());
        }
        
    //  Error
        @Ignore
        public void testGetBy_Error() {
            System.out.println("getBy - Error");

            Users user = new Users();
            user.setId(1);
            user.setUsername("iqbal");
            user.setPassword("iqbal123");

            when(daoLogin.getBy(user.getUsername(), user.getPassword())).thenReturn(new ArrayList<>());

            List<Users> result = daoLogin.getBy(user.getUsername(), user.getPassword());

            assertTrue(result.isEmpty()); 
        }
//  END TEST GET BY USERNAME AND PASSWORD

//  TEST INSERT USER
    //  Success
        @Test
        public void testInsertUser_Success() {
            System.out.println("insertUser - Success");

            Users newUser = new Users();
            newUser.setUsername("testing");
            newUser.setEmail("testing@gmail.com");
            newUser.setPassword("testing123");
            newUser.setAddress("");
            newUser.setPhoneNumber("");
            newUser.setSubstrict("");
            newUser.setWard("");
            newUser.setCreatedAt(new Date());
            newUser.setUpdatedAt(new Date());

            when(daoLogin.insertUser(newUser)).thenReturn(true);

            boolean result = daoLogin.insertUser(newUser);

            assertTrue(result);
        }
        
    //  Error
        @Test
        public void testInsertUser_Error() {
            System.out.println("insertUser - Error");

            Users newUser = new Users();
            newUser.setUsername("testing");
            newUser.setEmail("testing@gmail.com");
            newUser.setPassword("testing123");
            newUser.setAddress("");
            newUser.setPhoneNumber("");
            newUser.setSubstrict("");
            newUser.setWard("");
            newUser.setCreatedAt(new Date());
            newUser.setUpdatedAt(new Date());

            when(daoLogin.insertUser(newUser)).thenReturn(false);

            boolean result = daoLogin.insertUser(newUser);

            assertFalse(result);
        }
//  END INSERT USER
}
