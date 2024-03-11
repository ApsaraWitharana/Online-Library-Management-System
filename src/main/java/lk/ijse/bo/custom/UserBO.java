package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    List<User> getUserId();

    List<String> getUserName();
    List<String> getUserID() ;

    List<UserDTO> getAllUser() throws SQLException,ClassNotFoundException;

    boolean saveUser(UserDTO dto) throws SQLException,ClassNotFoundException;


    boolean updateUser(UserDTO dto) throws SQLException,ClassNotFoundException;


    boolean deleteUser(String id);

    UserDTO searchUser(int id) throws SQLException,ClassNotFoundException;


    Integer generateID(int id);


    Integer generateUserID(int id);


    ObservableList<UserDTO> getDetailsToTableView();


    User getLibraryAvailabilty(UserDTO userDTO);

    String getPassword(String userName);


    boolean isCorrect(String text, String text1);
}
