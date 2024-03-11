package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Library;
import org.hibernate.Session;



import java.util.List;

public interface LibraryDAO extends CrudDAO<Library,String> {
    List<Library> getAll() ;
    List<Library> getId();
    boolean save(Library entity);
    boolean update(Library entity) ;
    boolean delete(String id);
    Library search(int id) ;
    List<Library> getDetailsToTableView();
    void setSession(Session session);


}
