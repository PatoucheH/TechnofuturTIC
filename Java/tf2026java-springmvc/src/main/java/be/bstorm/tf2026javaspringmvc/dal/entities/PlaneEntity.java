package be.bstorm.tf2026javaspringmvc.dal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Plane")
@Table(
        uniqueConstraints = @UniqueConstraint(name= "uk_plane_imma", columnNames = {"imma"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneEntity extends BaseEntity<Long> {

    public PlaneEntity(Long id) {
        this.setId(id);
    }
    public PlaneEntity(Long id, String imma) {
        this.setId(id);
        this.imma = imma;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imma", nullable = false)
    private String imma;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "plane_type_id")
    private Long planeTypeId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FiscalEntity owner;

    @ManyToOne
    @JoinColumn(name = "plane_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PlaneTypeEntity planeType;
}
