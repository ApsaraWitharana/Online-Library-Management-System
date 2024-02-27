package lk.ijse.dao;

import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookDAO extends CrudDAO<Book> {

    ArrayList<Book> getAll();
    boolean save(Book entity);

    boolean update(Book entity);


    void delete(int id);

    Book search(int id) ;
}
