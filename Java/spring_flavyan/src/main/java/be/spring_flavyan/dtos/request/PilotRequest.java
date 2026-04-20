package be.spring_flavyan.dtos.request;

import be.spring_flavyan.entities.fiscal.Pilot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PilotRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String phoneNumber;
    private String brevetNumber;
    @Valid
    private AddressRequest address = new AddressRequest();

    public Pilot toEntity() {
        Pilot pilot = new Pilot();
        pilot.setName(name);
        pilot.setPhoneNumber(phoneNumber);
        pilot.setBrevetNumber(brevetNumber);
        if (address != null) {
            pilot.setAddress(address.toEntity());
        }
        return pilot;
    }
}
