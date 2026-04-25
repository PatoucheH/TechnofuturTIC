package be.firstapirest.dal.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

    private String country;

    private String zipCode;

    private String city;

    private String street;

    private Integer streetNumber;
}
