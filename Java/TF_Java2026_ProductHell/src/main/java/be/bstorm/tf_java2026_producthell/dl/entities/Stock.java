package be.bstorm.tf_java2026_producthell.dl.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Stock {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @Column(nullable = false)
    private int quantity;

    @Getter @Setter
    @Range
    private Integer threshold;

    public Stock(int quantity, int threshold) {
        this.quantity = quantity;
        this.threshold = threshold;
    }
}
