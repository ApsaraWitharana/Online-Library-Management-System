package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.IssueBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<IssueBook> IssueBook() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT i.issue_book_id,\n" +
                "i.user_id,\n" +
                "i.book_id , 'Loyalty book'as status\n" +
                "FROM issue_book i \n" +
                "WHERE current date <'2023-03-20'\n" +
                "Union\n" +
                "SELECT i.issue_book_id,\n" +
                "i.user_id,\n" +
                "i.book_id ,'Standard book'as status\n" +
                "FROM issue_book i \n" +
                "WHERE current date<'2023-03-30' ");

        List<IssueBook> issueBooks = query.list();
        transaction.commit();
        session.close();
        return issueBooks;

    }

    @Override
    public List<IssueBook> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" \n" +
                "FROM  issue_book i \n" +
                "JOIN\n" +
                "user u\n" +
                "ON i.user_user_id = u.user_id\n" +
                "JOIN\n" +
                "books b\n" +
                "ON i.book_book_id = b.book_id");


        List<IssueBook> issueBooks = query.list();
        transaction.commit();
        session.close();
        return issueBooks;
    }
}
