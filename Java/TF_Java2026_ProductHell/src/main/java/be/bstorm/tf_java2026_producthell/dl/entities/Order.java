package be.bstorm.tf_java2026_producthell.dl.entities;

import be.bstorm.tf_java2026_producthell.dl.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity @Table(name = "order_")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}) @ToString(of = {"id", "orderDate", "priority"})
public class Order {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter @Setter
    private LocalDateTime orderDate;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private User user;

    @Getter @Setter
    private boolean isPriority;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(User user, boolean isPriority) {
        this.orderDate = LocalDateTime.now();
        this.user = user;
        this.isPriority = isPriority;
        this.orderStatus = OrderStatus.PENDING;
    }

    public Order(User user, boolean isPriority, OrderStatus orderStatus) {
        this(user, isPriority);
        this.orderStatus = orderStatus;
    }
}
