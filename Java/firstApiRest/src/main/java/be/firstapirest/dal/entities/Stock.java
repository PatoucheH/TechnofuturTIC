package be.firstapirest.dal.entities;

import be.firstapirest.dal.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_stock")
@NoArgsConstructor @AllArgsConstructor
@Data
public class Stock extends BaseEntity<Long> {

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private Integer alertQuantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private List<StockMove> stockMove;

}
