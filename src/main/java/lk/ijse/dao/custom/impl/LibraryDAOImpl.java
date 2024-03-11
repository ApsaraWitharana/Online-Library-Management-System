package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.LibraryDAO;
import lk.ijse.entity.Library;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class LibraryDAOImpl implements LibraryDAO {


    private Session session;

    public LibraryDAOImpl(){

    }

    @Override
    public List<lk.ijse.entity.Library> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Library ");
        List<Library> libraries = query.list();
        transaction.commit();
        session.close();
        return libraries;
    }

    @Override
    public List<Library> getId() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT l.id FROM Library  l");
        List<Library> libraryId = query.list();
        transaction.commit();
        session.close();
        return libraryId;

    }

    @Override
    public Library getAll(String s) {
        Library library = session.get(Library.class,s);
        return library;
    }

    @Override
    public boolean save(Library library) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(library);
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
    public boolean update(Library library) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(library);
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
            Library library = session.get(Library.class,id);
            session.delete(library);
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
    public Library search(int id) {
        return null;
    }

    @Override
    public List<Library> getDetailsToTableView() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Library> dataList = session.createQuery("FROM Library",Library.class).list();
        transaction.commit();
        session.close();

        List<Library> observableList = FXCollections.observableArrayList(dataList);
        return observableList;
    }

    @Override
    public void setSession(Session session) {
         this.session=session;
    }
}
