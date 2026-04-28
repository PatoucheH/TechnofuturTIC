package be.bstorm.tf_java2026_producthell.dal.repositories;

import be.bstorm.tf_java2026_producthell.dl.entities.Order;
import be.bstorm.tf_java2026_producthell.dl.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLine.OrderLineId> {

    List<OrderLine> findByOrder(Order order);
}
