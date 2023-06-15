package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = GetBookByAuthorRequest.Builder.class)
public class GetBookByAuthorRequest {
    private final String author;

    public GetBookByAuthorRequest(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "GetBookByAuthorRequest{" +
                "author=" + author +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String author;


        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public GetBookByAuthorRequest build() {
            return new GetBookByAuthorRequest(author);
        }
    }
}
