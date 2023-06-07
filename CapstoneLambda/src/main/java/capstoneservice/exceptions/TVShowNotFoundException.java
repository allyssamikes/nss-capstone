package capstoneservice.exceptions;

public class TVShowNotFoundException extends RuntimeException {

    /**
     * Exception with no message or cause.
     */
    public TVShowNotFoundException() {
        super();
    }
    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public TVShowNotFoundException(String message) {
        super(message);
    }
    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public TVShowNotFoundException(Throwable cause) {
        super(cause);
    }
    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public TVShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

