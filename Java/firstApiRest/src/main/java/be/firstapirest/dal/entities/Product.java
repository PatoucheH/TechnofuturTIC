package be.firstapirest.dal.entities;

import be.firstapirest.dal.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_product")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity<Long> {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

}
