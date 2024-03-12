package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface SignupDAO extends SuperDAO {

    boolean save(String user_name, String password,String email) throws SQLException, ClassNotFoundException ;

    User search(String user_name) throws SQLException, ClassNotFoundException ;


    boolean update (User entity) throws SQLException, ClassNotFoundException ;


    boolean isCurrect(String name, String password,String email) throws SQLException, ClassNotFoundException;

    boolean isCurrect(User user);

    boolean save(User user);
}
