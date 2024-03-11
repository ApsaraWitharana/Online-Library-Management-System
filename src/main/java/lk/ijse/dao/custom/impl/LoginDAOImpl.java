package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;


public class LoginDAOImpl implements LoginDAO {

    private Session session;
    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("User is not save");
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public User search(String user_name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT U FROM User AS U WHERE U.password=:pass and U.name=:userName";
        try {
            Query query = session.createQuery(sql);
            query.setParameter("pass", password);
            query.setParameter("userName", user_name);
            User result = (User) query.getSingleResult();
            if (result != null) {
                System.out.println(result);
                System.out.println("Login Success");
                transaction.commit();
                return true;
            }else {

            }
            transaction.rollback();
            return false;
        /*}
        try {
            Query query = session.createQuery("SELECT u FROM User u WHERE u.password=:pass and u.u_name=:userName");
            query.setParameter("pass", password);
            query.setParameter("userName", user_name);
            query.executeUpdate();
            transaction.commit();
            return true;*/
        } catch (Exception e) {

            return false;
        } finally {
            session.close();
        }
    }



    @Override
    public boolean isCurrectUser(User user) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("User is not save");
            return false;
        } finally {
            session.close();
        }
    }
}
