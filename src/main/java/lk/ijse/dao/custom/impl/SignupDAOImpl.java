package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.SignupDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.SQLException;

public class SignupDAOImpl implements SignupDAO {
    private Session session;


    @Override
    public boolean save(String name, String password,String email) throws SQLException, ClassNotFoundException {

        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("SELECT u.password,u.name,u.email FROM User u WHERE u.password=:pass and u.name=:name and u.email=:email");
            query.setParameter("pass", password);
            query.setParameter("name", name);
            query.setParameter("email", email);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();

        }
    }

    @Override
    public User search(String user_name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean isCurrect(String name, String password,String email) throws SQLException, ClassNotFoundException {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("SELECT u.password,u.name,u.email FROM User u WHERE u.password=:pass and u.name=:name and u.email=:email");
            query.setParameter("password", password);
            query.setParameter("name", name);
            query.setParameter("email", email);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean isCurrect(User user) {
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
    public boolean save(User user) {
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
