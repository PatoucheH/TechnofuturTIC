package be.bstorm.tf_java2026_producthell.dl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "role_")
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Role {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @Column(unique = true, nullable = false, length = 50)
    private String name;
}
