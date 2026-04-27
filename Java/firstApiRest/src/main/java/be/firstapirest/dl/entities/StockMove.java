package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import be.firstapirest.dl.enums.TypeMove;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_stock_move")
@NoArgsConstructor @AllArgsConstructor
@Data
public class StockMove extends BaseEntity<Long> {

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMove type;

    @Column(nullable = true)
    private String description;

}
