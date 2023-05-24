package activity.request;

public class RemoveBookFromCurrentlyReadingRequest {

    private final String isbn;
    private final String userId;

    public RemoveBookFromCurrentlyReadingRequest(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "RemoveBookFromCurrentlyReadingRequest{" +
                "isbn='" + isbn + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String isbn;
        private String userId;

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public RemoveBookFromCurrentlyReadingRequest build(){
            return new RemoveBookFromCurrentlyReadingRequest(isbn, userId);
        }
    }
}
