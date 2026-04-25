package be.firstapirest.dal.entities;

import be.firstapirest.dal.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_category")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Category extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String name;
}
