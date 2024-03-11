package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface SignupBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException ;

    UserDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(User entity) throws SQLException, ClassNotFoundException ;

    boolean updateUser (User dto) throws SQLException, ClassNotFoundException ;


    boolean isCurrectUser(String user_name, String email, String password) throws SQLException, ClassNotFoundException;
}


