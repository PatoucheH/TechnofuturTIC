package be.spring_flavyan.entities.fiscal;

import be.spring_flavyan.entities.FlightRelation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Entity
@Data
public class Pilot extends Fiscal {

    @NotBlank(message = "Brevet number is required ! ")
    private String brevetNumber;

    @OneToMany(mappedBy = "pilot",  cascade = CascadeType.ALL)
    private List<FlightRelation> flightRelations = new ArrayList<>();

    public Pilot(){}
    public Pilot(String name){
        super(name);
    }
    public Pilot(String name, Address address, String phoneNumber, String brevetNumber){
        super(name, address, phoneNumber);
        this.brevetNumber = brevetNumber;
    }

}
