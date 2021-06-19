package by.intro.library.service;

import by.intro.library.model.Book;
import by.intro.library.model.Customer;
import by.intro.library.model.exception.BookIsAlreadyAssignedException;
import by.intro.library.model.exception.CustomerNotFoundException;
import by.intro.library.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookService bookService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookService bookService) {
        this.customerRepository = customerRepository;
        this.bookService = bookService;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(id));
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
        return customer;
    }

    @Transactional
    public Customer editCustomer(Long id, Customer customer) {
        Customer customerToEdit = getCustomer(id);
        customerToEdit.setCustomerName(customer.getCustomerName());
        return customerToEdit;
    }

    @Transactional
    public Customer addBookToCustomer(Long customerId, Long bookId) {
        Customer customer = getCustomer(customerId);
        Book book = bookService.getBook(bookId);
        if (Objects.nonNull(book.getCustomer())) {
            throw new BookIsAlreadyAssignedException(bookId, book.getCustomer().getId());
        }
        customer.addBook(book);
        book.setCustomer(customer);
        return customer;
    }

    @Transactional
    public Customer removeBookFromCustomer(Long customerId, Long bookId) {
        Customer customer = getCustomer(customerId);
        Book book = bookService.getBook(bookId);
        customer.removeBook(book);
        book.setCustomer(null);
        return customer;
    }
}
