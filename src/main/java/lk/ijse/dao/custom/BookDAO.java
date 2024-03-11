package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface BookDAO extends CrudDAO<Book,String> {

    List<Book> getAll();

    List<Book> getId();

    boolean save(Book entity);

    boolean update(Book entity);

    boolean delete(String id);


    int generateID(int book_id);

    Book search(int id) ;

    List<Book> getDetailsToTableView();

    void setSession(Session session);

    List<String> getBookID();

    boolean exist(String book_id);

    BookDTO getTitle();
}
