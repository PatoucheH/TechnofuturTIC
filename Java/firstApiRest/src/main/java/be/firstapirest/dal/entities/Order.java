package be.firstapirest.dal.entities;

import be.firstapirest.dal.entities.base.BaseEntity;
import be.firstapirest.dal.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "t_order")
@NoArgsConstructor @AllArgsConstructor
@Data
public class Order extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Double totalPrice;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List<CommandLine> commandLines;

}
