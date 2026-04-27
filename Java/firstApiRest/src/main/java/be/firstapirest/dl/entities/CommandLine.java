package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_command_line")
@AllArgsConstructor @NoArgsConstructor
@Data
public class CommandLine extends BaseEntity<Long> {

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
