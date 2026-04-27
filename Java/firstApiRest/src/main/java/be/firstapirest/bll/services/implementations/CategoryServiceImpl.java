package be.firstapirest.bll.services.implementations;

import be.firstapirest.bll.services.implementations.base.AbstractCRUDImpl;
import be.firstapirest.bll.services.interfaces.CategoryService;
import be.firstapirest.dl.entities.Category;
import be.firstapirest.dal.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl    extends AbstractCRUDImpl<Category, Long, CategoryRepository>
                                    implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public List<Category> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public void update(Long id, Category category) {
        Category categoryToUpdate =  findById(id);
        categoryToUpdate.setName(category.getName());
        repository.save(categoryToUpdate);
    }

}
