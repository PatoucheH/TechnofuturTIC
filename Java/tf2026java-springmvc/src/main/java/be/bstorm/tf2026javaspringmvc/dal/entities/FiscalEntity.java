package be.bstorm.tf2026javaspringmvc.dal.entities;

import be.bstorm.tf2026javaspringmvc.dal.embbed.Address;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity(name = "Fiscal")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class FiscalEntity extends BaseEntity<Long> {
    private String name;
    private String phoneNumber;
    @Embedded
    private Address address;
}
