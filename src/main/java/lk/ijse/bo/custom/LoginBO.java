package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException ;

    UserDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException ;

}
