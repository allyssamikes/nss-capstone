package capstoneservice.activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetBookRequest {

    private final String isbn;

    public GetBookRequest(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
    @Override
    public String toString() {
        return "GetBookRequest{" +
                "isbn='" + isbn + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String isbn;


        public Builder withIsbn(String isbn) {
            this.isbn= isbn;
            return this;
        }


        public GetBookRequest build() {
            return new GetBookRequest(isbn);
        }
    }
}
