package be.bstorm.tf_java2026_producthell.dl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Category {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
