package be.bstorm.dal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Plane")
@Table(
        name = "t_plane",
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

    @ManyToOne
    private FiscalEntity owner;
}
