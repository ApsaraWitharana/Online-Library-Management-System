package lk.ijse.bo;

import lk.ijse.dto.BookDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface BookBO extends SuperBO{


    ArrayList<BookDTO> getAllBook() throws SQLException,ClassNotFoundException;

    boolean saveBook(BookDTO dto) throws SQLException,ClassNotFoundException;


    boolean updateBook(BookDTO dto) throws SQLException,ClassNotFoundException;


    void deleteBook(int id) throws SQLException,ClassNotFoundException;

    BookDTO searchBook(int id) throws SQLException,ClassNotFoundException;
}
