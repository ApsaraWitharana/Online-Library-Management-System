package lk.ijse.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() ;

    boolean save(T entity);

    boolean update(T entity) ;

    void delete(int id);

    T search(int id) ;

}
