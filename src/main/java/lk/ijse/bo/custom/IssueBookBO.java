package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.entity.IssueBook;

import java.sql.SQLException;
import java.util.List;

public interface IssueBookBO  extends SuperBO {

    List<IssueBook> getIssueBookId();

    List<IssueBookDTO> getAllIssueBook() throws SQLException,ClassNotFoundException;

    boolean issueIssueBook(IssueBookDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateIssueBook(IssueBookDTO dto) throws SQLException,ClassNotFoundException;


    boolean deleteIssueBook(String id);

    IssueBookDTO searchIssueBook(int id) throws SQLException,ClassNotFoundException;




    ObservableList<IssueBookDTO> getDetailsToTableView();


    IssueBook getIssueBookAvailabilty(IssueBookDTO issueBookDTO);

    boolean saveIssueBook(IssueBookDTO issueBookDTO);
}
