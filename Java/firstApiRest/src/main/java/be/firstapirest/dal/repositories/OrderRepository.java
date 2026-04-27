package be.firstapirest.dal.repositories;

import be.firstapirest.dl.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
