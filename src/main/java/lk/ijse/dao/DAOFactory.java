package lk.ijse.dao;

import lk.ijse.dao.custom.LibraryDAO;
import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        BOOK,USER,LIBRARY,ISSUE_BOOK,USER_REGISTRATION,SIGNUP,LOGIN,SETTING
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER : return new UserDAOImpl();
            case USER_REGISTRATION:return new UserDAOImpl();
            case ISSUE_BOOK:return new IssueBookDAOImpl();
            case LIBRARY:return new LibraryDAOImpl();
            case BOOK: return new BookDAOImpl();
            case SIGNUP:return new SignupDAOImpl();
            case LOGIN:return new LoginDAOImpl();
            case SETTING:return new LoginDAOImpl();

            default:return null;


        }
    }
}
