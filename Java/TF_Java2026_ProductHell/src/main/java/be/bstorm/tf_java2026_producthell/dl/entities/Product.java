package be.bstorm.tf_java2026_producthell.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"}) @ToString(of = {"id", "name", "price"})
public class Product {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Getter @Setter
    @Column(nullable = false)
    @Range(min = 0 , max = Integer.MAX_VALUE)
    private int price;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Stock stock;

    public Product(String name, int price, Category category, Stock stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }
}
