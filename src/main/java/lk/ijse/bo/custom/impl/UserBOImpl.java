package lk.ijse.bo.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl  implements UserBO {

   private Session session ;
   UserDAO userDAO= new UserDAOImpl();

    @Override
    public List<User> getUserId() {
        return userDAO.getId();
    }

    @Override
    public List<String> getUserName() {
       return userDAO.getUserName();
    }

    @Override
    public List<String> getUserID() {
        return userDAO.getUserID();
    }

    @Override
    public List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {

        List<User> users = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User list : users) {
            userDTOS.add(new UserDTO(list.getId(),list.getName(),list.getAddress(),list.getEmail(),list.getContact(),list.getGender()));

        }
        return userDTOS;
    }

    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact(),dto.getGender()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact(),dto.getGender()));
    }

    @Override
    public boolean deleteUser(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            userDAO.delete(id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("usertype deleting process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public UserDTO searchUser(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Integer generateID(int id) {
        return null;
    }

    @Override
    public Integer generateUserID(int id) {
        return null;
    }

    @Override
    public ObservableList<UserDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDAO.setSession(session);
            List<User> userList = userDAO.getDetailsToTableView();
            ObservableList<UserDTO> userObList = FXCollections.observableArrayList();

            for (User user : userList) {
                userObList.add(
                        new UserDTO(user.getId(),user.getName(),user.getAddress(),user.getEmail(),user.getContact(),user.getGender()));

            }
            transaction.commit();
            session.close();
            return userObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User getLibraryAvailabilty(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDAO.setSession(session);
            User user = userDAO.getAll(String.valueOf(userDTO.getId()));
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getRoomAvailability failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String getPassword(String userName) {
        return null;
    }

    @Override
    public boolean isCorrect(String text, String text1) {
        return false;
    }


//    public List<IssueUserProjection> getUserProjection() {
//
////        1.constructor object ekk
////       2.interface projection -abstact geter method hdl krnne-native sql wl one
//
//        String sql = "SELECT new projection.IssueBookProjection(B.id,B.name)\n" +
//                "FROM Book AS B";
//        Query query = session.createQuery(sql);
//        List list = query.list();
//        session.close();
//        return list;
//    }
}
