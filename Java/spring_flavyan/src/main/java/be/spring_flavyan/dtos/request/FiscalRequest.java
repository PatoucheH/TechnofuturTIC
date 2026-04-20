package be.spring_flavyan.dtos.request;

import be.spring_flavyan.entities.fiscal.Fiscal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FiscalRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String phoneNumber;
    @Valid
    private AddressRequest address = new AddressRequest();

    public Fiscal toEntity() {
        Fiscal fiscal = new Fiscal();
        fiscal.setName(name);
        fiscal.setPhoneNumber(phoneNumber);
        if (address != null) {
            fiscal.setAddress(address.toEntity());
        }
        return fiscal;
    }
}
