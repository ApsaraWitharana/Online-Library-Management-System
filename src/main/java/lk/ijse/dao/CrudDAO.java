package lk.ijse.dao;

import java.time.LocalDate;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{
   T getAll(ID id) ;

    boolean save(T entity);

    boolean update(T entity) ;

    boolean delete(ID id);

    T search(int id) ;

    List<T> getDetailsToTableView();


}
