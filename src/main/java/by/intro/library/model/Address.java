package by.intro.library.model;

import by.intro.library.model.dto.AddressDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    public Address() {
    }

    public static Address from(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        return address;
    }

}
