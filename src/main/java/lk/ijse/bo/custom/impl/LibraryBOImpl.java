package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.LibraryBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.LibraryDAO;
import lk.ijse.dao.custom.impl.LibraryDAOImpl;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.entity.Library;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBOImpl implements LibraryBO {

    private Session session;

    LibraryDAO libraryDAO = (LibraryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LIBRARY);

    @Override
    public List<Library> getLibraryId() {
        return libraryDAO.getId();
    }

    @Override
    public List<LibraryDTO> getAllLibrary() throws SQLException, ClassNotFoundException {
        List<Library> libraries = libraryDAO.getAll();
        List<LibraryDTO> libraryDTOS = new ArrayList<>();
        for (Library list : libraries) {
            libraryDTOS.add(new LibraryDTO(list.getId(),list.getName(),list.getAddress(),list.getEmail(),list.getContact()));

        }
        return libraryDTOS;
    }

    @Override
    public boolean saveLibrary(LibraryDTO dto) throws SQLException, ClassNotFoundException {
        return libraryDAO.save(new Library(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }

    @Override
    public boolean updateLibrary(LibraryDTO dto) throws SQLException, ClassNotFoundException {
        return libraryDAO.update(new Library(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));

    }

    @Override
    public boolean deleteLibrary(String id) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            libraryDAO.setSession(session);
            libraryDAO.delete(id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("library type deleting process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public LibraryDTO searchLibrary(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Integer generateID(int id) {
        return null;
    }

    @Override
    public Integer generateLibraryID(int id) {
        return null;
    }

    @Override
    public ObservableList<LibraryDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            libraryDAO.setSession(session);
            List<Library> libraryList = libraryDAO.getDetailsToTableView();
            ObservableList<LibraryDTO> libraryObList = FXCollections.observableArrayList();

            for (Library library : libraryList) {
                libraryObList.add(
                        new LibraryDTO(library.getId(),library.getName(),library.getAddress(),library.getEmail(),library.getContact())
                );
            }
            transaction.commit();
            session.close();
            return libraryObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Library getLibraryAvailabilty(LibraryDTO libraryDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            libraryDAO.setSession(session);
            Library library = libraryDAO.getAll(String.valueOf(libraryDTO.getId()));
            transaction.commit();
            session.close();
            return library;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getRoomAvailability failed");
            System.out.println(e);
            return null;
        }
    }
}
