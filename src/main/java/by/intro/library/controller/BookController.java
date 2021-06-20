package by.intro.library.controller;

import by.intro.library.model.Book;
import by.intro.library.model.BookPage;
import by.intro.library.model.dto.BookDto;
import by.intro.library.service.BookService;
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
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody final BookDto bookDto) {
        Book book = bookService.addBook(Book.from(bookDto));
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getBooks(BookPage bookPage) {
        Page<Book> books = bookService.getBooks(bookPage);
        Pageable pageable = books.getPageable();
        List<BookDto> booksDto = books.stream().map(BookDto::from).collect(Collectors.toList());
        PageImpl<BookDto> bookDtoPage = new PageImpl<>(booksDto, pageable, books.getTotalElements());
        return new ResponseEntity<>(bookDtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable final Long id) {
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable final Long id) {
        Book book = bookService.deleteBook(id);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<BookDto> editBook(@PathVariable final Long id,
                                            @RequestBody final BookDto bookDto) {
        Book editedBook = bookService.editBook(id, Book.from(bookDto));
        return new ResponseEntity<>(BookDto.from(editedBook), HttpStatus.OK);
    }
}
