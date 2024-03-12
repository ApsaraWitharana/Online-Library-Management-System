package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface LoginBO extends SuperBO {

    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException ;

    UserDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException ;

    List<String> getUserNameList();

    String getPassword(String userName);

    User getUser(String userName);
}
