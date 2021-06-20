package by.intro.library.model;

import by.intro.library.model.dto.CustomerDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Book> borrowBooks = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public void addBook(Book book) {
        borrowBooks.add(book);
    }

    public void removeBook(Book book) {
        borrowBooks.remove(book);
    }

    public static Customer from(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        return customer;
    }

    public void addAddress(Address addr) {
        address = addr;
    }

    public void removeAddress() {
        address = null;
    }
}
