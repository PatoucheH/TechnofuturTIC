package be.spring_flavyan.entities;

import be.spring_flavyan.entities.base.BaseEntity;
import be.spring_flavyan.entities.fiscal.Fiscal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Plane extends BaseEntity<Long> {
    private String imma;
    private LocalDate buyDate;

    @ManyToOne
    @JoinColumn(name = "plane_type_id")
    private PlaneType planeType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Fiscal owner;

    public Plane(){}

    public Plane(String imma, PlaneType planeType, Fiscal owner){
        this();
        this.imma = imma;
        this.planeType =  planeType;
        this.owner = owner;
    }

    public Plane(String imma, LocalDate buyDate, PlaneType planeType, Fiscal owner) {
        this(imma, planeType, owner);
        this.buyDate = buyDate;
    }

}