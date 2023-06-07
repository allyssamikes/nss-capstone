package capstoneservice.exceptions;

public class BookNotFoundException extends RuntimeException {

    /**
     * Exception with no message or cause.
     */
    public BookNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public BookNotFoundException(String message) {
        super(message);
    }


    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public BookNotFoundException(Throwable cause) {
        super(cause);
    }
    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
