package be.bstorm.tf2026javaspringmvc.dal.entities.fiscals;

import be.bstorm.tf2026javaspringmvc.dal.entities.FiscalEntity;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneTypeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity(name = "Mechanic")
@Data
public class MechanicEntity extends FiscalEntity {

    @ManyToMany
    private List<PlaneTypeEntity> planeTypes;
}
