package by.intro.library.model.dto;

import by.intro.library.model.Customer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomerDto {

    private Long id;
    private String customerName;
    private List<BookDto> borrowBooksDto = new ArrayList<>();

    public static CustomerDto from(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setBorrowBooksDto(customer.getBorrowBooks()
                .stream().map(BookDto::from).collect(Collectors.toList()));
        return customerDto;
    }
}
