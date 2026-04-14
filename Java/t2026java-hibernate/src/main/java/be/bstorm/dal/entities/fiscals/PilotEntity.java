package be.bstorm.dal.entities.fiscals;

import be.bstorm.dal.entities.FiscalEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Pilot")
@Table(name = "t_pilot")
@Data
public class PilotEntity extends FiscalEntity {
    private String licenseNumber;
}
