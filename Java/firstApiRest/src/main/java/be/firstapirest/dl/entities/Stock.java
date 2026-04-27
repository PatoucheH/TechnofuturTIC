package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
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



}
