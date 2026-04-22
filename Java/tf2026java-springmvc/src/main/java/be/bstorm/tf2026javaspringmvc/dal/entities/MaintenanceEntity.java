package be.bstorm.tf2026javaspringmvc.dal.entities;

import be.bstorm.tf2026javaspringmvc.dal.entities.fiscals.MechanicEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Entity(name = "Maintenance")
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uk_maintenance_number", columnNames = "number")
)
@Data
public class MaintenanceEntity extends BaseEntity<Long> {
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "duration", nullable = false)
    private Duration duration;

    @ManyToOne
    private MechanicEntity reparer;
    @ManyToOne
    private MechanicEntity verifier;
    @ManyToOne
    private PlaneEntity plane;
}
