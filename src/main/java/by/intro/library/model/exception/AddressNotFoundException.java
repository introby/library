package by.intro.library.model.exception;

import java.text.MessageFormat;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find address with id: {0}", id));
    }
}
