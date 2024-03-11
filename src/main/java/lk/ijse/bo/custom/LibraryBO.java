package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.entity.Library;

import java.sql.SQLException;
import java.util.List;

public interface LibraryBO extends SuperBO {

    List<Library> getLibraryId();

    List<LibraryDTO> getAllLibrary() throws SQLException,ClassNotFoundException;

    boolean saveLibrary(LibraryDTO dto) throws SQLException,ClassNotFoundException;


    boolean updateLibrary(LibraryDTO dto) throws SQLException,ClassNotFoundException;


    boolean deleteLibrary(String id);

    LibraryDTO searchLibrary(int id) throws SQLException,ClassNotFoundException;


    Integer generateID(int id);


    Integer generateLibraryID(int id);


    ObservableList<LibraryDTO> getDetailsToTableView();


    Library getLibraryAvailabilty(LibraryDTO libraryDTO);

}
