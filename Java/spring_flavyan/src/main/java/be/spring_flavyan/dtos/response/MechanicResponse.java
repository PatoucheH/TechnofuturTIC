package be.spring_flavyan.dtos.response;

import be.spring_flavyan.entities.fiscal.Mechanic;
import lombok.Value;

@Value
public class MechanicResponse {
    Long id;
    String name;
    String phoneNumber;
    AddressResponse address;

    public static MechanicResponse from(Mechanic mechanic) {
        return new MechanicResponse(
                mechanic.getId(),
                mechanic.getName(),
                mechanic.getPhoneNumber(),
                AddressResponse.from(mechanic.getAddress())
        );
    }
}
