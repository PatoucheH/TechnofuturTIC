package be.spring_flavyan.entities.fiscal;

import be.spring_flavyan.entities.Intervention;
import be.spring_flavyan.entities.PlaneType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Mechanic extends Fiscal {

    @ManyToMany
    @JoinTable(
            name = "mechanic_can_repair",
            joinColumns = @JoinColumn(name = "mechanic_id"),
            inverseJoinColumns = @JoinColumn(name = "plane_type_id")
    )
    private List<PlaneType> planeTypesReparable = new ArrayList<>();

    @OneToMany(mappedBy = "repairer")
    private Set<Intervention> interventionsRepairer = new HashSet<>();
    @OneToMany(mappedBy = "verifier")
    private Set<Intervention> interventionsVerifier = new HashSet<>();


    public Mechanic() {}
    public Mechanic(String name){
        super(name);
    }
    public Mechanic(String name, Address address, String phoneNumber) {
        super(name, address, phoneNumber);
    }
}