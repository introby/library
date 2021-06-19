package by.intro.library.model;

import by.intro.library.model.dto.CustomerDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "card_id")
//    private MembershipCard membershipCard;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Book> borrowBooks = new ArrayList<>();

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
}
