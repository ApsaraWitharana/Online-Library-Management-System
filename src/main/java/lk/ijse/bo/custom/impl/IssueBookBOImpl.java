package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.IssueBookBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.IssueBookDAO;
import lk.ijse.dao.custom.impl.IssueBookDAOImpl;
import lk.ijse.dto.IssueBookDTO;
import lk.ijse.entity.IssueBook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueBookBOImpl implements IssueBookBO {

    BookDAO bookDAO = new BookDAOImpl();
    IssueBookDAO issueBookDAO = new IssueBookDAOImpl();
    @Override
    public List<IssueBook> getIssueBookId() {
        return null;
    }

    @Override
    public List<IssueBookDTO> getAllIssueBook() throws SQLException, ClassNotFoundException {
        List<IssueBook> issueBooks = issueBookDAO.getAll();
        List<IssueBookDTO> issueBookDTOS = new ArrayList<>();
        for (IssueBook list : issueBooks) {
             issueBookDTOS.add(new IssueBookDTO(list.getAvailable(),list.getDay_count(),list.getDate(),list.getBook(),list.getUser()));

        }
        return issueBookDTOS;
    }


    @Override
    public boolean issueIssueBook(IssueBookDTO dto) throws SQLException, ClassNotFoundException {
        return issueBookDAO.issue(new IssueBook(dto.getB_id(),dto.getU_id(),dto.getDate(),dto.getDay_count(),dto.getAvailable()));
    }

    @Override
    public boolean updateIssueBook(IssueBookDTO dto) throws SQLException, ClassNotFoundException {
        return issueBookDAO.update(new IssueBook(dto.getB_id(),dto.getU_id(),dto.getAvailable(),dto.getDay_count(),dto.getDate()));

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
        return null;
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

        try {
            issueBookDAO.setSession(session);
            bookDAO.setSession(session);
            issueBookDAO.save(issueBookDTO.toEntity());
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


}
