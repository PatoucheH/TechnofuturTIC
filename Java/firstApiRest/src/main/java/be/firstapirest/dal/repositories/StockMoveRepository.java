package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.StockMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMoveRepository extends JpaRepository<StockMove, Long> {
}
