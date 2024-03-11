package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;



    @Override
    public List<User> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM User ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return  users;
    }

    @Override
    public List<User> getId() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT u.id FROM User  u");
        List<User> userId = query.list();
        transaction.commit();
        session.close();
        return userId;

    }

    @Override
    public User getAll(String s) {
        return null;
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

    @Override
    public boolean update(User user) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("failed");
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class,id);
            session.delete(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("failed");
            return false;
        } finally {
            session.close();

        }
    }

    @Override
    public User search(int id) {
        return null;
    }

    @Override
    public List<User> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> dataList = session.createQuery("FROM User",User.class).list();
        transaction.commit();
        session.close();

        List<User> observableList = FXCollections.observableArrayList(dataList);
        return observableList;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<String> getUserID() {

        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.id FROM User  u");
        List<String> userType = query.list();
        session.close();
        return  userType;
    }

    @Override
    public List<String> getUserName() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.name FROM User  u");
        List<String> userType = query.list();
        session.close();
        return  userType;
    }

    @Override
    public String getPassword(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.password FROM User u WHERE u.userName = :userName");
        query.setParameter("userName", userName);
        String password =  (String) query.uniqueResult();
        session.close();
        return password;
    }

    @Override
    public boolean updateUserName(String text, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE User u SET u.userName= :user WHERE u.userName = :userName");
            query.setParameter("user", text);
            query.setParameter("userName", userName);
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
    public boolean updatePassword(String text, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE User u SET u.password= :pass WHERE u.userName = :userName");
            query.setParameter("pass", text);
            query.setParameter("userName", userName);
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


}

