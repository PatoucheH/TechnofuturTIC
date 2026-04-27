package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_category")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter @Setter
public class Category extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String name;
}
