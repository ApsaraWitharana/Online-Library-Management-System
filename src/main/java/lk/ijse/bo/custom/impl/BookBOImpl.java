package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import lk.ijse.projection.IssueBookProjection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    private Session session;
    BookDAO bookDAO = new BookDAOImpl();
    @Override
    public BookDTO getTitle() {
        return bookDAO.getTitle();
    }
    @Override
    public List<Book> getBookId() {
        return bookDAO.getId();
    }

    @Override
    public List<String> getBookID() {
        return bookDAO.getBookID();

    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> books = bookDAO.getAll();
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book list : books) {
            bookDTOS.add(new BookDTO(list.getId(), list.getTitle(), list.getStream(), list.getAuthor(), list.getStatus(), list.getQty()));

        }
        return bookDTOS;
    }

    @Override
    public boolean saveBook(BookDTO dto) {
        return bookDAO.save(new Book(dto.getId(), dto.getTitle(), dto.getStream(), dto.getAuthor(), dto.getStatus(), dto.getQty()));
    }

    @Override
    public boolean updateBook(BookDTO dto) {
        return bookDAO.update(new Book(dto.getId(), dto.getTitle(), dto.getStream(), dto.getAuthor(), dto.getStatus(), dto.getQty()));
    }

    @Override
    public boolean deleteBook(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookDAO.setSession(session);
            bookDAO.delete(id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("book type deleting process failed");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public BookDTO searchBook(String id) {
        Book book = bookDAO.search(Integer.parseInt(id));
        return new BookDTO(book.getId(), book.getTitle(), book.getStream(), book.getAuthor(), book.getStatus(), book.getQty());
    }


    @Override
    public Integer generateID(int id) {
        return bookDAO.generateID(id);
    }


    @Override
    public Integer generateBookID(int id) {
        return bookDAO.generateID(id);
    }

    public ObservableList<BookDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookDAO.setSession(session);
            List<Book> bookList = bookDAO.getDetailsToTableView();
            ObservableList<BookDTO> bookObList = FXCollections.observableArrayList();

            for (Book book : bookList) {
                bookObList.add(
                        new BookDTO(book.getId(), book.getTitle(), book.getStream(), book.getAuthor(), book.getStatus(), book.getQty())
                );
            }
            transaction.commit();
            session.close();
            return bookObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Book getBookAvailabilty(BookDTO bookDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookDAO.setSession(session);
            Book book = bookDAO.getAll(String.valueOf(bookDTO.getId()));
            transaction.commit();
            session.close();
            return book;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getRoomAvailabilty failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean exisBook(String book_id) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookDAO.setSession(session);
            bookDAO.exist(book_id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("issue book type deleting process failed");
            System.out.println(e);
            return false;
        }

    }

    public List<IssueBookProjection> getIssueProjection() {

//        1.constructor object ekk
//       2.interface projection -abstact geter method hdl krnne-native sql wl one

        String sql = "SELECT new projection.IssueBookProjection(B.id,B.name)\n" +
                "FROM IssueBook AS B";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

}