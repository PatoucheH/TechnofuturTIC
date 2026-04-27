package be.firstapirest.dal.repositories;

import be.firstapirest.dl.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByNameContaining(String name);
}
