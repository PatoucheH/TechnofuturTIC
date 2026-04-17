package be.spring_flavyan.entities;

import be.spring_flavyan.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class PlaneType extends BaseEntity<Long> {
    private String name;
    private String constructorName;
    private Integer enginePower;
    private Integer nbPlaces;

    @OneToMany(mappedBy = "planeType")
    private Set<Plane> planes = new HashSet<>();

    public PlaneType(String name, String constructorName, Integer enginePower, Integer nbPlaces) {
        this.name = name;
        this.constructorName = constructorName;
        this.enginePower = enginePower;
        this.nbPlaces = nbPlaces;
    }
}