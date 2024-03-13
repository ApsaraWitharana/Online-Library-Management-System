package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AdminDAOImpl implements AdminDAO {

    private Session session;
    @Override
    public boolean isCurrectUser(String name, String password) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT a.password,a.name FROM Admin a WHERE a.password=:pass and a.name=:name";
        try {
            Query query = session.createQuery(sql);
            query.setParameter("pass", password);
            query.setParameter("name", name);
            session.isConnected();
            transaction.commit();
            return true;

        } catch (Exception e) {

            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateAdminName(String text, String name) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE Admin a SET a.name= :admin WHERE a.name = :name");
            query.setParameter("admin", text);
            query.setParameter("name", name);
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
    public boolean delete(String name) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Admin admin = session.get(Admin.class,name);
            session.delete(admin);
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
    public boolean updateName(String text, String name) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE Admin a SET a.name= :admin WHERE a.name = :name");
            query.setParameter("admin", text);
            query.setParameter("name", name);
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
