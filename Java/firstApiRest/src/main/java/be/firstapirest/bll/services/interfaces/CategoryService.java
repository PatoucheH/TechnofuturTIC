package be.firstapirest.bll.services.interfaces;

import be.firstapirest.bll.services.interfaces.base.BaseCRUDService;
import be.firstapirest.dal.entities.Category;

import java.util.List;

public interface CategoryService extends BaseCRUDService<Category, Long> {

    List<Category> findByName(String name);
    void update(Long id, Category category);
}
