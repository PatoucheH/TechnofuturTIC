package be.spring_flavyan.entities;

import be.spring_flavyan.entities.base.BaseRelation;
import be.spring_flavyan.entities.fiscal.Pilot;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class FlightRelation extends BaseRelation<FlightRelation.FlightId> {

    @Embeddable
    @Data
    public static class FlightId implements Serializable {
        private Long planeTypeId;
        private Long pilotId;
    }

    @ManyToOne
    @MapsId("planeTypeId")
    @JoinColumn(name = "plane_type_id")
    private PlaneType planeType;

    @ManyToOne
    @MapsId("pilotId")
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    private double nbHour;
}
