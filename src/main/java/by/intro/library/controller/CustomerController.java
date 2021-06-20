package by.intro.library.controller;

import by.intro.library.model.Customer;
import by.intro.library.model.CustomerPage;
import by.intro.library.model.dto.CustomerDto;
import by.intro.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody final CustomerDto customerDto) {
        Customer customer = customerService.addCustomer(Customer.from(customerDto));
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getCustomers(CustomerPage customerPage) {
        Page<Customer> customers = customerService.getCustomers(customerPage);
        Pageable pageable = customers.getPageable();
        List<CustomerDto> customersDto = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
        PageImpl<CustomerDto> customerDtoPage = new PageImpl<>(customersDto, pageable, customers.getTotalElements());
        return new ResponseEntity<>(customerDtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable final Long id) {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable final Long id) {
        Customer customer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable final Long id,
                                                    @RequestBody final CustomerDto customerDto) {
        Customer customer = customerService.editCustomer(id, Customer.from(customerDto));
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @PostMapping(value = "{customerId}/books/{bookId}/add")
    public ResponseEntity<CustomerDto> addBookToCustomer(@PathVariable final Long customerId,
                                                         @PathVariable final Long bookId) {
        Customer customer = customerService.addBookToCustomer(customerId, bookId);
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }

    @DeleteMapping(value = "{customerId}/books/{bookId}/remove")
    public ResponseEntity<CustomerDto> removeBookFromCustomer(@PathVariable final Long customerId,
                                                         @PathVariable final Long bookId) {
        Customer customer = customerService.removeBookFromCustomer(customerId, bookId);
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }
}
