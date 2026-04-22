package be.bstorm.tf2026javaspringmvc.dal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "PlaneType")
@Data
public class PlaneTypeEntity extends BaseEntity<Long> {
    private String name;
    private String constructor;
    private Integer enginePower;
    private Integer maxPassengers;

}
