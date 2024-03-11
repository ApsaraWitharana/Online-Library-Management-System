package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignupBO;
import lk.ijse.dao.custom.SignupDAO;
import lk.ijse.dao.custom.impl.SignupDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {


    SignupDAO signupDAO = new SignupDAOImpl();
    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return signupDAO.save(new User(dto.getName(),dto.getPassword(),dto.getEmail()));

    }

    @Override
    public UserDTO searchUser(String u_name) throws SQLException, ClassNotFoundException {
        User user = (User) signupDAO.search(String.valueOf(u_name));
        return new UserDTO(user.getName(),user.getPassword(),user.getEmail());
    }

    @Override
    public boolean isCurrectUser(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(User dto) throws SQLException, ClassNotFoundException {
        return signupDAO.update(new User(dto.getName(),dto.getPassword(),dto.getEmail()));

    }

    @Override
    public boolean isCurrectUser(String user_name, String email, String password) throws SQLException, ClassNotFoundException {
        return signupDAO.isCurrect(new User(user_name,email,password));

    }
}
