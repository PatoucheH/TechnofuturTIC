package be.spring_flavyan.dtos.request;

import be.spring_flavyan.entities.fiscal.Address;
import be.spring_flavyan.validation.ValidZip;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ValidZip
public class AddressRequest {
    private String street;
    private String city;
    private String zip;
    private String country;
    private String state;

    public Address toEntity() {
        return new Address(street, city, zip, country, state);
    }
}
