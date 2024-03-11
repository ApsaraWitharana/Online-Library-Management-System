package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SettingBO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

public class SettingBOImpl implements SettingBO {

UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(),userDTO.getName(),userDTO.getAddress(),userDTO.getEmail(),userDTO.getContact(),userDTO.getGender(),userDTO.getUserName(),userDTO.getPassword());
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(),userDTO.getName(),userDTO.getAddress(),userDTO.getEmail(),userDTO.getContact(),userDTO.getGender(),userDTO.getUserName(),userDTO.getPassword());
        return userDAO.update(user);
    }

    @Override
    public boolean updateUserName(String text, String userName) {
        return userDAO.updateUserName(text, userName);
    }

    @Override
    public boolean updatePassword(String text, String userName) {
        return userDAO.updatePassword(text, userName);
    }

}
