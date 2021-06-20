package by.intro.library.model;

import by.intro.library.model.dto.BookDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String name;

    @ManyToOne
    private Customer customer;

    public static Book from(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setName(bookDto.getName());
        return book;
    }
}
