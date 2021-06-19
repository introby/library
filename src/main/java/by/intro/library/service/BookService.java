package by.intro.library.service;

import by.intro.library.model.Book;
import by.intro.library.model.exception.BookNotFoundException;
import by.intro.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(id));
    }

    public Book deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
        return book;
    }

    @Transactional
    public Book editBook(Long id, Book book) {
        Book bookToEdit = getBook(id);
        bookToEdit.setName(book.getName());
        bookToEdit.setAuthor(book.getAuthor());
        return bookToEdit;
    }
}
