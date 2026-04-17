package be.spring_flavyan.entities;
import be.spring_flavyan.entities.base.BaseEntity;
import be.spring_flavyan.entities.fiscal.Mechanic;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_intervention_number", columnNames = "number"))
@Data
public class Intervention extends BaseEntity<Long> {
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Duration duration;

    @ManyToOne
    private Mechanic repairer;
    @ManyToOne
    private Mechanic verifier;
    @ManyToOne
    private Plane plane;

}