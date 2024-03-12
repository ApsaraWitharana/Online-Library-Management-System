package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.IssueBookDAO;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.entity.IssueBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class IssueBookDAOImpl implements IssueBookDAO {

    private Session session;
    @Override
    public IssueBook getAll(String s) {
        IssueBook issueBook = session.get(IssueBook.class,s);
        return issueBook;
    }

    @Override
    public boolean save(IssueBook issueBook) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(issueBook);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("Library is not save");
            return false;
        } finally {
            session.close();
        }
    }


    @Override
    public List<IssueBook> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query sql = session.createQuery(" \n" +
                "SELECT * FROM  issue_book i\n" +
                "JOIN\n" +
                "user u\n" +
                "ON i.user_user_id = u.user_id\n" +
                "JOIN\n" +
                "books b\n" +
                "ON i.book_book_id = b.book_id");

//        Query query  =   session.createQuery(query);
//        query.setParameter("user_id",user_user_id);
        List<IssueBook> issueBooks = sql.list();
        transaction.commit();
        session.close();
        return issueBooks;
    }

    @Override
    public List<IssueBook> getId() {
        return null;
    }

    @Override
    public boolean issue(IssueBook issueBook) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(issueBook);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("IssueBook is not save");
            return false;
        } finally {
            session.close();
        }
    }



    @Override
    public boolean update(IssueBook issueBook) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(issueBook);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("IssueBook is not save");
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
            IssueBook issueBook = session.get(IssueBook.class,id);
            session.delete(issueBook);
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
    public IssueBook search(int id) {
        return null;
    }

    @Override
    public List<IssueBook> getDetailsToTableView() {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }



}
