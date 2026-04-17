package be.spring_flavyan.entities.fiscal;

import be.spring_flavyan.entities.Transaction;
import be.spring_flavyan.entities.base.BaseEntity;
import be.spring_flavyan.entities.Plane;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"ownedPlanes", "purchases", "sales"})
@Inheritance(strategy = InheritanceType.JOINED)
public class Fiscal extends BaseEntity<Long> {
    @NotBlank
    private String name;
    @Embedded
    private Address address;
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private Set<Plane> ownedPlanes = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<Transaction> purchases = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<Transaction> sales = new HashSet<>();

    public Fiscal(){}
    public Fiscal(String name) {
        this();
        this.name = name;
    }
    public Fiscal(String name, Address address, String phoneNumber) {
        this(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}