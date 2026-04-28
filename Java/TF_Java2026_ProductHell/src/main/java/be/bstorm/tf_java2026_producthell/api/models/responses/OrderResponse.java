package be.bstorm.tf_java2026_producthell.api.models.responses;

import be.bstorm.tf_java2026_producthell.dl.entities.Order;
import be.bstorm.tf_java2026_producthell.dl.entities.OrderLine;
import be.bstorm.tf_java2026_producthell.dl.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        LocalDateTime orderDate,
        boolean isPriority,
        OrderStatus orderStatus,
        List<OrderLineResponse> lines
) {

    public static OrderResponse from(Order order, List<OrderLine> orderLines) {
        return new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.isPriority(),
                order.getOrderStatus(),
                orderLines.stream()
                        .map(l -> new OrderLineResponse(
                                l.getProduct().getId(),
                                l.getProduct().getName(),
                                l.getQuantity()
                        ))
                        .toList()
        );
    }

    public record OrderLineResponse(UUID productId, String productName, int quantity) {
    }
}
