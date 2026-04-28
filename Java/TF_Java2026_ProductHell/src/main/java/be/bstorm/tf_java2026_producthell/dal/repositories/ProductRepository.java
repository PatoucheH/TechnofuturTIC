package be.bstorm.tf_java2026_producthell.dal.repositories;

import be.bstorm.tf_java2026_producthell.dl.entities.Product;
import be.bstorm.tf_java2026_producthell.dl.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(
            value = "SELECT p FROM Product p JOIN p.category",
            countQuery = "SELECT COUNT(p) FROM Product p"
    )
    Page<Product> findProductsPageWithCategory(Pageable pageable);

//    @EntityGraph(attributePaths = "category")
//    @Query("SELECT p FROM Product p")
//    Page<Product> findProductsPageWithCategory(Pageable pageable);

}
