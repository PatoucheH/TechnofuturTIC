package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import be.firstapirest.dl.entities.base.BaseEntityUUID;
import be.firstapirest.dl.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_order")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
@Data
public class Order extends BaseEntityUUID {

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
