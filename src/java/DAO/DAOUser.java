/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.KonserKuyUtil;
import pojo.UserDetail;
import pojo.Users;
/**
 *
 * @author ACO
 */
public class DAOUser {
    public Users getUserById(int userId) {
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        Users user = null;
        try {
            user = (Users) session.get(Users.class, userId);
        } finally {
            session.close();
        }
        return user;
    }

    public List<UserDetail> getByUserId(int userId) {
        List<UserDetail> listUserDetail = new ArrayList<>();
        Transaction trans = null;
        Session session = null;

        try {
            session = KonserKuyUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            Query query = session.createQuery("FROM UserDetail u WHERE u.userId = :userId");
            query.setParameter("userId", userId);

            listUserDetail = query.list(); 

            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback(); 
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return listUserDetail;
    }

    public boolean insertUser(UserDetail newUser) {
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(newUser); 
            trans.commit();
            System.out.println("UserDetail inserted successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            if (trans != null) {
                trans.rollback(); 
            }
            return false;
        } finally {
            session.close();
        }
    }
    
    public boolean updateUser(UserDetail user) {
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.update(user); 
            trans.commit();
            System.out.println("User updated successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            if (trans != null) {
                trans.rollback(); 
            }
            return false;
        } finally {
            session.close();
        }
    }
    
}
