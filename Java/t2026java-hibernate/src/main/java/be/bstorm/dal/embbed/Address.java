package be.bstorm.dal.embbed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    @Column(name = "address_number", nullable = false)
    private String number;
    @Column(name = "address_street", nullable = false)
    private String street;
    @Column(name = "address_city", nullable = false)
    private String city;
    @Column(name = "address_zip", nullable = false)
    private String zip;
    @Column(name = "address_country", nullable = false)
    private String country;
    @Column(name = "address_state", nullable = false)
    private String state;
}
