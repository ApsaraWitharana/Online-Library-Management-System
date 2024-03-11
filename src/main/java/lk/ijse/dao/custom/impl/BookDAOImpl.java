package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Session session;

    public BookDAOImpl() {

    }

    @Override
    public List<Book> getAll() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Book ");
        List<Book> books = query.list();
        transaction.commit();
        session.close();
        return books;


    }

    @Override
    public List<Book> getId() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT b.id FROM Book  b");
        List<Book> bookId = query.list();
        transaction.commit();
        session.close();
        return bookId;

    }


    @Override
    public Book getAll(String s) {
        Book book = session.get(Book.class,s);
        return book;
    }

    @Override
    public boolean save(Book book) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
            System.out.println("Book is not save");
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean update(Book book) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(book);
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
            Book book = session.get(Book.class,id);
            session.delete(book);
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
    public int generateID(int book_id) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT b.name FROM Book b WHERE b.id = :book_id");
        query.setParameter("book_id", book_id);
        String id = (String) query.getSingleResult();
        transaction.commit();
        session.close();
        return Integer.parseInt(id);

    }


    @Override
    public Book search(int id) {
        return null;
    }

//    public ObservableList<Book> getDetailsToTableView() {
//
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        List<Book> dataList = session.createQuery("FROM Book", Book.class).list();
//        transaction.commit();
//        session.close();
//
//        ObservableList<Book> observableList = FXCollections.observableArrayList(dataList);
//        return observableList;
//    }

    @Override
    public List<Book> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Book> dataList = session.createQuery("FROM Book",Book.class).list();
        transaction.commit();
        session.close();

        List<Book> observableList = FXCollections.observableArrayList(dataList);
        return observableList;

    }

    @Override
    public void setSession(Session session) {
       this.session=session;
    }

    @Override
    public List<String> getBookID() {

        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT b.id FROM Book  b");
        List<String> bookType = query.list();
        session.close();
        return  bookType;
    }

    @Override
    public boolean exist(String book_id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Book book = session.get(Book.class,book_id);
            session.delete(book);
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
    public BookDTO getTitle() {

        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT b.title FROM Book  b");
        List<String> bookType1 = query.list();
        session.close();
        return (BookDTO) bookType1;
    }
}
