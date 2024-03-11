package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Library;
import lk.ijse.entity.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {

    List<User> getAll();
    List<User> getId();
    boolean save(User entity);
    boolean update(User entity) ;
    boolean delete(String id);
    User search(int id) ;
    List<User> getDetailsToTableView();
    void setSession(Session session);
    List<String> getUserID();
    List<String> getUserName();
    String getPassword(String userName);

    boolean updateUserName(String text, String userName);

    boolean updatePassword(String text, String userName);


}
