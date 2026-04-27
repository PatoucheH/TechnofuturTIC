package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import be.firstapirest.dl.entities.base.BaseEntityUUID;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_product")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Product extends BaseEntityUUID {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<StockMove> stockMove;

}
