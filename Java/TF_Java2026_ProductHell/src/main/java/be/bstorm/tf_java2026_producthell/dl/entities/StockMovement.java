package be.bstorm.tf_java2026_producthell.dl.entities;

import be.bstorm.tf_java2026_producthell.dl.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "movementType"}) @ToString(of = {"id", "quantity", "movementType"})
public class StockMovement {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    @Range
    private int quantity;

    @Getter @Setter
    @Column(nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Product product;

    public StockMovement(int quantity, MovementType movementType, Product product) {
        this.quantity = quantity;
        this.movementType = movementType;
        this.product = product;
    }
}

