package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
