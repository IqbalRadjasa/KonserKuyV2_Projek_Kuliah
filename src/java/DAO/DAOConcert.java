package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Concert;
import pojo.ConcertTicket;
import pojo.ConcertTrailer;
import pojo.ConcertView;
import pojo.KonserKuyUtil;
import pojo.PaymentHistory;
import pojo.Users;

public class DAOConcert {
    public List<ConcertTicket> getBy(int userId) {
        ConcertTicket u = new ConcertTicket();
        List<ConcertTicket> listTicket = new ArrayList<>();

        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from ConcertTicket ct where ct.userId= :userId");
            query.setParameter("userId", userId); 
            listTicket = query.list();

            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            System.out.println("Error: " + e);
            listTicket = null;
        }
        return listTicket;
    }

    public List<Concert> retrieveTblConcert() {
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Concert> concertList = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(
                "SELECT c FROM Concert c " +
                "LEFT JOIN FETCH c.concertView cv " +
                "LEFT JOIN FETCH c.concertTrailer ct"
            );
            concertList = query.list(); 

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return concertList;
    }
    
    public Concert getConcertById(int concertId) {
        Concert concert = null; // Initialize Concert object
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            Query query = session.createQuery(
                "SELECT c FROM Concert c " +
                "LEFT JOIN FETCH c.concertView cv " +
                "LEFT JOIN FETCH c.concertTrailer ct " +
                "WHERE c.id = :concertId"
            );

            query.setParameter("concertId", concertId); 

            concert = (Concert) query.uniqueResult();

            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            e.printStackTrace();
            concert = null;
        } finally {
            session.close();
        }
        return concert; 
    }

    public boolean insert(ConcertTicket ticket) {
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(ticket); 
            trans.commit();
            System.out.println("Booking Success");
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

    public boolean insertHistory(PaymentHistory history) {
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(history); 
            trans.commit();
            System.out.println("History Added");
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

    public List<PaymentHistory> getHistoryByUserId(int userId) {
        List<PaymentHistory> listHistory = new ArrayList<>();
        Transaction trans = null;
        Session session = KonserKuyUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();

            Query query = session.createQuery("from PaymentHistory ph where ph.userId = :userId");
            query.setParameter("userId", userId);

            listHistory = query.list();

            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback(); // Rollback in case of an error
            }
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close(); // Ensure the session is closed to free resources
        }
        return listHistory;
    }

    public boolean deleteById(int historyId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = KonserKuyUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            PaymentHistory history = (PaymentHistory) session.get(PaymentHistory.class, historyId);

            if (history != null) {
                session.delete(history);
                transaction.commit();
                return true;  // Successfully deleted
            } else {
                return false; // History record not found
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
            return false; // Exception occurred
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }
    }
}
