package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.AdminDAO;
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
}
