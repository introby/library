package by.intro.library.model.dto;

import by.intro.library.model.Address;
import lombok.Data;


@Data
public class AddressDto {

    private Long id;
    private String street;

    public static AddressDto from(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());

        return addressDto;
    }
}
