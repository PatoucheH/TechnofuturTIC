package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContaining(String name);
}
