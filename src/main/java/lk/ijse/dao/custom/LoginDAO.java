package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface LoginDAO extends SuperDAO {

    boolean save(User entity) throws SQLException, ClassNotFoundException ;

    User search(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String name, String password) throws SQLException, ClassNotFoundException ;



    boolean isCurrectUser(User user);

    List<String> getUserNameList();

    String getPassword(String user_name);

    User searchh(String txtUserName);


}
