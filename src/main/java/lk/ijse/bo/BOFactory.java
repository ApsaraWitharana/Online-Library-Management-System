package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.IssueBookBOImpl;
import lk.ijse.bo.custom.impl.LibraryBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {


    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory=new BOFactory():boFactory;

    }

    public enum BOType{
        BOOK,LIBRARY,USER,ISSUE_BOOK,USER_REGISTRATION
    }

    public SuperBO getBO(BOType boType){
        switch (boType){

            case BOOK :return new BookBOImpl();
            case USER:return new UserBOImpl();
            case LIBRARY:return new LibraryBOImpl();
            case ISSUE_BOOK:return new IssueBookBOImpl();
            case USER_REGISTRATION:return new UserBOImpl();

            default:return null;
        }
    }
}
