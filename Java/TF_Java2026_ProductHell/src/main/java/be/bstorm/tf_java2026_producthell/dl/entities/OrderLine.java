package be.bstorm.tf_java2026_producthell.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}) @ToString
public class OrderLine {

    @EmbeddedId
    @Getter
    private final OrderLineId id = new OrderLineId();

    @Getter @Setter
    @Range
    private int quantity;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @MapsId("productId")
    private Product product;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @MapsId("orderId")
    private Order order;

    public OrderLine(int quantity, Product product, Order order) {
        this.quantity = quantity;
        this.setProduct(product);
        this.setOrder(order);
    }

    public void setProduct(Product product) {
        this.product = product;
        this.id.setProductId(product.getId());
    }

    public void setOrder(Order order) {
        this.order = order;
        this.id.setOrderId(order.getId());
    }

    @Embeddable
    @NoArgsConstructor @AllArgsConstructor
    @EqualsAndHashCode @ToString
    @Getter @Setter
    public static class OrderLineId{

        private UUID productId;
        private UUID orderId;
    }
}
