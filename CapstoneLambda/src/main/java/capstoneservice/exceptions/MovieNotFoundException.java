package capstoneservice.exceptions;

public class MovieNotFoundException extends RuntimeException {
    /**
     * Exception with no message or cause.
     */
    public MovieNotFoundException() {
        super();
    }
    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public MovieNotFoundException(String message) {
        super(message);
    }
    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }
    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
