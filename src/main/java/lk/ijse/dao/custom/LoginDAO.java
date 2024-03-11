package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {

    boolean save(User entity) throws SQLException, ClassNotFoundException ;

    User search(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException ;



    boolean isCurrectUser(User user);
}
