package be.bstorm.dal.entities.fiscals;

import be.bstorm.dal.entities.FiscalEntity;
import be.bstorm.dal.entities.PlaneTypeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity(name = "Mechanic")
@Table(name = "t_mechanic")
@Data
public class MechanicEntity extends FiscalEntity {

    @ManyToMany
    private List<PlaneTypeEntity> planeTypes;
}
