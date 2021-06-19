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
    @Column(name = "book_id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Customer customer;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "book_genre",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
//    private Set<Genre> genres;

    public static Book from(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setName(bookDto.getName());
        return book;
    }
}
