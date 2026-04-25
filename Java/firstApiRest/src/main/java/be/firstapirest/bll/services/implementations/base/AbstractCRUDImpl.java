package be.firstapirest.bll.services.implementations.base;

import be.firstapirest.bll.services.interfaces.base.BaseCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCRUDImpl<T, ID, U extends JpaRepository<T, ID>>
                                                implements BaseCRUDService<T, ID> {

    protected final U repository;

    @Override
    public T findById(ID id){
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<T> findAll(){
        return repository.findAll();
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
