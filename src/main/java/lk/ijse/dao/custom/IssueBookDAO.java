package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.entity.IssueBook;
import lk.ijse.entity.Library;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public interface IssueBookDAO extends CrudDAO<IssueBook,String> {
    List<IssueBook> getAll() ;
    List<IssueBook> getId();

    boolean update( IssueBook issueBook);

    boolean delete(String id);
    IssueBook search(int id) ;
    List<IssueBook> getDetailsToTableView();
    void setSession(Session session);

    boolean issue(IssueBook issueBook);


    String getNextId();

}
