package be.spring_flavyan.entities.fiscal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {
    @Column(name = "addres_street")
    private String street;
    @Column(name = "address_city")
    private String city;
    @Column(name = "address_zip")
    private String zip;
    @Column(name = "address_country")
    private String country;
    @Column(name = "address_state")
    private String state;

}
