package be.bstorm.tf_java2026_producthell.dal.repositories;

import be.bstorm.tf_java2026_producthell.dl.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
