package be.bstorm.dal.entities;

import be.bstorm.dal.embbed.Address;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Fiscal")
@Table(name = "t_fiscal")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class FiscalEntity extends BaseEntity<Long> {
    private String name;
    private String phoneNumber;
    @Embedded
    private Address address;
}
