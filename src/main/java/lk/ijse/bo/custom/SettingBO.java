package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

public interface SettingBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO);

    boolean updateUserName(String text, String userName);

    boolean updatePassword(String text, String userName);


}
