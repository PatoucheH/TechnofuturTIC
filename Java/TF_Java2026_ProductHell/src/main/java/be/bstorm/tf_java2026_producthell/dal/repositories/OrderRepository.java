package be.bstorm.tf_java2026_producthell.dal.repositories;

import be.bstorm.tf_java2026_producthell.dl.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
