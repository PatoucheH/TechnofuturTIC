package be.spring_flavyan.dtos.request;

import be.spring_flavyan.entities.fiscal.Mechanic;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MechanicRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String phoneNumber;
    @Valid
    private AddressRequest address = new AddressRequest();

    public Mechanic toEntity() {
        Mechanic mechanic = new Mechanic();
        mechanic.setName(name);
        mechanic.setPhoneNumber(phoneNumber);
        if (address != null) {
            mechanic.setAddress(address.toEntity());
        }
        return mechanic;
    }
}
