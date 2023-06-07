package capstoneservice.activity.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = RemoveBookFromToReadListRequest.Builder.class)
public class RemoveBookFromToReadListRequest {

    private final String isbn;
    private final String userId;

    public RemoveBookFromToReadListRequest(String isbn, String userId) {
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
        return "RemoveBookFromToReadListRequest{" +
                "isbn='" + isbn + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    @JsonPOJOBuilder
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

            public RemoveBookFromToReadListRequest build(){
                return new RemoveBookFromToReadListRequest(isbn, userId);
            }
        }
    }