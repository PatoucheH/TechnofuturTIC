package be.spring_flavyan.dtos.response;

import be.spring_flavyan.entities.fiscal.Fiscal;
import lombok.Value;

@Value
public class FiscalResponse {
    Long id;
    String name;
    String phoneNumber;
    AddressResponse address;

    public static FiscalResponse from(Fiscal fiscal) {
        return new FiscalResponse(
                fiscal.getId(),
                fiscal.getName(),
                fiscal.getPhoneNumber(),
                AddressResponse.from(fiscal.getAddress())
        );
    }
}
