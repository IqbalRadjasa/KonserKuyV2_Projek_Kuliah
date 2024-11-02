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
import pojo.Users;

/**
 *
 * @author ACO
 */
public class DAOLogin {
    public List<Users> getBy(String uName, String uPass) {
        Users u = new Users();
        List<Users> listTblUser = new ArrayList<>();

        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Users where username= :uName AND password= :uPass");
            query.setString("uName", uName);
            query.setString("uPass", uPass);
            u = (Users) query.uniqueResult();
            listTblUser = query.list();

            trans.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        return listTblUser;
    }
    
    public boolean insertUser(Users newUser) {
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(newUser); 
            trans.commit();
            System.out.println("User inserted successfully");
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
