package lk.ijse.dao.custom;

import lk.ijse.entity.IssueBook;

import java.util.List;

public interface QueryDAO {
    List<IssueBook> IssueBook();

    List<IssueBook> getAll();
}
