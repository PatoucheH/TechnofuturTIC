package be.firstapirest.bll.services.interfaces.base;

import java.util.List;

public interface BaseCRUDService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void deleteById(ID id);

}
