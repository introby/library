package by.intro.library.service;

import by.intro.library.model.Book;
import by.intro.library.model.BookPage;
import by.intro.library.model.exception.BookNotFoundException;
import by.intro.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Page<Book> getBooks(BookPage bookPage) {
        Sort sort = Sort.by(bookPage.getSortDirection(), bookPage.getSortBy());
        Pageable pageable = PageRequest.of(bookPage.getPageNumber(),
                bookPage.getPageSize(),
                sort);
        return bookRepository.findAll(pageable);
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
