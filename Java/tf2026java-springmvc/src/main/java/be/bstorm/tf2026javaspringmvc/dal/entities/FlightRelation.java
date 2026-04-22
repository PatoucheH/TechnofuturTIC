package be.bstorm.tf2026javaspringmvc.dal.entities;

import be.bstorm.tf2026javaspringmvc.dal.entities.fiscals.PilotEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;

@Entity(name = "Flight")
@Data
public class FlightRelation extends BaseRelation<FlightRelation.FlightId>{

    @Embeddable
    @Data
    public static class FlightId implements Serializable {
        private Long planeTypeId;
        private Long pilotId;
    }

    @ManyToOne
    @MapsId("planeTypeId")
    private PlaneTypeEntity planeType;
    @ManyToOne
    @MapsId("pilotId")
    private PilotEntity pilot;

    private Duration duration;
}
