package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.IssueBookDAO;
import lk.ijse.dao.custom.impl.IssueBookDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.IssueBook;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueBookBOImpl implements IssueBookBO {

    BookDAO bookDAO = new BookDAOImpl();
    IssueBookDAO issueBookDAO = new IssueBookDAOImpl();

    QueryDAO queryDAO = new QueryDAOImpl();
    @Override
    public List<IssueBook> getIssueBookId() {
        return null;
    }

    @Override
    public List<IssueBookDTO> getAllIssueBook() throws SQLException, ClassNotFoundException {
        List<IssueBook> issueBooks = issueBookDAO.getAll();
        List<IssueBookDTO> issueBookDTOS = new ArrayList<>();
        for (IssueBook list : issueBooks) {
             issueBookDTOS.add(new IssueBookDTO(list.getId(),list.getAvailable(),list.getDay_count(), list.getDate(),list.getBook(),list.getUser()));

        }
        return issueBookDTOS;
    }


    @Override
    public boolean issueIssueBook(IssueBookDTO dto) throws SQLException, ClassNotFoundException {
        return issueBookDAO.issue(new IssueBook(dto.getB_id(),dto.getU_id(), dto.getDate(),dto.getDay_count(),dto.getAvailable(),dto.getId()));
    }

    @Override
    public boolean updateIssueBook(IssueBookDTO dto) throws SQLException, ClassNotFoundException {
        return issueBookDAO.update(new IssueBook(dto.getB_id(),dto.getU_id(),dto.getAvailable(),dto.getDay_count(), String.valueOf(dto.getDate())));

    }

    @Override
    public boolean deleteIssueBook(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            issueBookDAO.setSession(session);
            issueBookDAO.delete(id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("issue book type deleting process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public IssueBookDTO searchIssueBook(int id) throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public ObservableList<IssueBookDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            issueBookDAO.setSession(session);
            List<IssueBook> booksList = issueBookDAO.getDetailsToTableView();
            ObservableList<IssueBookDTO> bookObList = FXCollections.observableArrayList();

            for (IssueBook issueBook : booksList) {
                bookObList.add(
                        new IssueBookDTO(issueBook.getAvailable(),issueBook.getDay_count(), issueBook.getDate(),issueBook.getId(),issueBook.getUser(),issueBook.getBook()));

            }
            transaction.commit();
            session.close();
            return bookObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public IssueBook getIssueBookAvailabilty(IssueBookDTO issueBookDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            issueBookDAO.setSession(session);
            IssueBook issueBook = issueBookDAO.getAll(issueBookDTO.getB_id());
            transaction.commit();
            session.close();
            return issueBook;
        }catch (Exception e ){
            transaction.rollback();
            session.close();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean saveIssueBook(IssueBookDTO issueBookDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        IssueBook issueBook = new IssueBook();
        try {
//            issueBookDAO.setSession(session);
//            queryDAO.getAll();
            issueBookDAO.save(issueBook);
            updateIssueBook(issueBookDTO);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("issue book save process failed");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getIssueId() {
        return issueBookDAO.getNextId();
    }


}
