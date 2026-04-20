package be.spring_flavyan.dtos.response;

import be.spring_flavyan.entities.fiscal.Pilot;
import lombok.Value;

@Value
public class PilotResponse {
    Long id;
    String name;
    String phoneNumber;
    String brevetNumber;
    AddressResponse address;

    public static PilotResponse from(Pilot pilot) {
        return new PilotResponse(
                pilot.getId(),
                pilot.getName(),
                pilot.getPhoneNumber(),
                pilot.getBrevetNumber(),
                AddressResponse.from(pilot.getAddress())
        );
    }
}
