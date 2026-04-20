package be.spring_flavyan.dtos.response;

import be.spring_flavyan.entities.fiscal.Address;
import lombok.Value;

import java.util.stream.Stream;

@Value
public class AddressResponse {
    String street;
    String city;
    String zip;
    String country;
    String state;

    public static AddressResponse from(Address address) {
        if (address == null) return null;
        return new AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getZip(),
                address.getCountry(),
                address.getState()
        );
    }

    @Override
    public String toString() {
        return Stream.of(street, city, zip, country, state)
                .filter(s -> s != null && !s.isBlank())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
