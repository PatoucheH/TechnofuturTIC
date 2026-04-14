package be.bstorm.dal.entities;

import be.bstorm.dal.entities.fiscals.MechanicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity(name = "PlaneType")
@Table(name = "t_plane_type")
@Data
public class PlaneTypeEntity extends BaseEntity<Long> {
    private String name;
    private String constructor;
    private Integer enginePower;
    private Integer maxPassengers;

}
