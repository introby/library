package by.intro.library.model.dto;

import by.intro.library.model.Customer;
import lombok.Data;

@Data
public class PlainCustomerDto {

    private Long id;
    private String customerName;

    public static PlainCustomerDto from(Customer customer) {
        PlainCustomerDto plainCustomerDto = new PlainCustomerDto();
        plainCustomerDto.setId(customer.getId());
        plainCustomerDto.setCustomerName(customer.getCustomerName());
        return plainCustomerDto;
    }
}
