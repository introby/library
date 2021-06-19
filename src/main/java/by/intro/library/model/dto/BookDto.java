package by.intro.library.model.dto;

import by.intro.library.model.Book;
import lombok.Data;

import java.util.Objects;

@Data
public class BookDto {

    private Long id;
    private String author;
    private String name;
    private PlainCustomerDto plainCustomerDto;

    public static BookDto from(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setName(book.getName());
        if (Objects.nonNull(book.getCustomer())) {
            bookDto.setPlainCustomerDto(PlainCustomerDto.from(book.getCustomer()));
        }
        return bookDto;
    }
}
