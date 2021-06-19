package by.intro.library.model.exception;

import java.text.MessageFormat;

public class BookIsAlreadyAssignedException extends RuntimeException {

    public BookIsAlreadyAssignedException(final Long bookId, Long customerId) {
        super(MessageFormat.format("Book: {0} is already assigned to Customer: {1}", bookId, customerId));
    }
}
