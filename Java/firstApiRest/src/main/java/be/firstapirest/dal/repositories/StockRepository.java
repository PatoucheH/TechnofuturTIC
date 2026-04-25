package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
