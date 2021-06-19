package by.intro.library.model.exception;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find book with id: {0}", id));
    }
}
