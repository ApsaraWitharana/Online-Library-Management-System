package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.dao.custom.impl.LoginDAOImpl;

import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = new LoginDAOImpl();
    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return loginDAO.save(new User(dto.getName(),dto.getPassword()));
    }

    @Override
    public UserDTO searchUser(String user_name) throws SQLException, ClassNotFoundException {

        User user = (User) loginDAO.search(String.valueOf(user_name));
        return new UserDTO(user.getName(),user.getPassword(),user.getEmail());
    }

    @Override
    public boolean isCurrectUser(String name, String password) throws SQLException, ClassNotFoundException {
        return loginDAO.isCurrectUser(new User(name,password));
    }

    @Override
    public List<String> getUserNameList() {
       return loginDAO.getUserNameList();
    }

    @Override
    public String getPassword(String user_name) {
        return loginDAO.getPassword(user_name);
    }

    @Override
    public User getUser(String txtUserName) {
      return loginDAO.searchh(txtUserName);
    }
}
