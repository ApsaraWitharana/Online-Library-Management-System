package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Book;

public interface AdminDAO {
    boolean isCurrectUser(String text, String text1);

    boolean updateAdminName(String text, String name);

    boolean delete(String name);

    boolean updateName(String text, String name);

}
