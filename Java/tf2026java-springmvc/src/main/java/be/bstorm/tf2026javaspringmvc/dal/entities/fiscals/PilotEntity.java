package be.bstorm.tf2026javaspringmvc.dal.entities.fiscals;

import be.bstorm.tf2026javaspringmvc.dal.entities.FiscalEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Pilot")
@Data
public class PilotEntity extends FiscalEntity {
    private String licenseNumber;
}
