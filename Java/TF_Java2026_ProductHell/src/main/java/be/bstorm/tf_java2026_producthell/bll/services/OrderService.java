package be.bstorm.tf_java2026_producthell.bll.services;

import be.bstorm.tf_java2026_producthell.api.models.requests.OrderRequest;
import be.bstorm.tf_java2026_producthell.api.models.responses.OrderResponse;
import be.bstorm.tf_java2026_producthell.dal.repositories.*;
import be.bstorm.tf_java2026_producthell.dl.entities.*;
import be.bstorm.tf_java2026_producthell.dl.enums.MovementType;
import be.bstorm.tf_java2026_producthell.dl.enums.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StockMovementRepository stockMovementRepository;

    public List<OrderResponse> getAll(){
        List<OrderResponse> result = new ArrayList<>();
        List<Order> orders =  orderRepository.findAll();
        orders.forEach(order -> {
            List<OrderLine> orderLines = orderLineRepository.findByOrder(order);
            result.add(OrderResponse.from(order, orderLines));
        });

        return result;
     }

    public List<OrderResponse> placeOrder(OrderRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + request.userId()));
        Map<Product, Integer> available = new LinkedHashMap<>();
        Map<Product, Integer> unavailable = new LinkedHashMap<>();
        for (var lineReq : request.lines()) {
            Product product = productRepository.findById(lineReq.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found: " + lineReq.productId()));
            Stock stock = product.getStock();
            if (stock != null && stock.getQuantity() >= lineReq.quantity()) {
                available.put(product, lineReq.quantity());
            } else {
                unavailable.put(product, lineReq.quantity());
            }
        }
        if (unavailable.isEmpty()) {
            OrderWithLines result = buildOrder(user, available, false, OrderStatus.CONFIRMED);
            decrementStock(available);
            return List.of(OrderResponse.from(result.order(), result.lines()));
        }
        List<OrderResponse> responses = new ArrayList<>();
        if (!available.isEmpty()) {
            OrderWithLines result = buildOrder(user, available, false, OrderStatus.CONFIRMED);
            decrementStock(available);
            responses.add(OrderResponse.from(result.order(), result.lines()));
        }
        OrderWithLines priorityResult = buildOrder(user, unavailable, true, OrderStatus.PENDING);
        responses.add(OrderResponse.from(priorityResult.order(), priorityResult.lines()));
        return responses;
    }

    private OrderWithLines buildOrder(User user, Map<Product, Integer> items, boolean isPriority, OrderStatus status) {
        Order order = new Order(user, isPriority, status);
        orderRepository.save(order);
        List<OrderLine> lines = new ArrayList<>();
        items.forEach((product, quantity) -> {
            OrderLine line = new OrderLine(quantity, product, order);
            orderLineRepository.save(line);
            lines.add(line);
        });
        return new OrderWithLines(order, lines);
    }

    private void decrementStock(Map<Product, Integer> items) {
        items.forEach((product, quantity) -> {
            product.getStock().setQuantity(product.getStock().getQuantity() - quantity);
            StockMovement movement = new StockMovement(quantity, MovementType.OUT, product);
            stockMovementRepository.save(movement);
        });
    }

    private record OrderWithLines(Order order, List<OrderLine> lines) {
    }

    public void changeOrderStatus(UUID id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
        order.setOrderStatus(orderStatus);
    }
}
