package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import lk.ijse.projection.IssueBookProjection;

import java.sql.SQLException;
import java.util.List;


public interface BookBO extends SuperBO {

    BookDTO getTitle();


    List<Book> getBookId();
    List<String> getBookID();
    List<BookDTO> getAllBook() throws SQLException,ClassNotFoundException;

    boolean saveBook(BookDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateBook(BookDTO dto) throws SQLException,ClassNotFoundException;

    boolean deleteBook(String id);

    BookDTO searchBook(String id);

    Integer generateID(int id);

    Integer generateBookID(int id);

    ObservableList<BookDTO> getDetailsToTableView();

    Book getBookAvailabilty(BookDTO bookDTO);
    boolean exisBook(String book_id);

    public List<IssueBookProjection> getIssueProjection() ;
}
