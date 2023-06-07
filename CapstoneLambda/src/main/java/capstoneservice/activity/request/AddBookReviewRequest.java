package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddBookReviewRequest.Builder.class)
public class AddBookReviewRequest {
    private final String isbn;

    private final String userId;

    private final String UUIDOfEntity;

    public AddBookReviewRequest(String isbn, String userId, String UUIDOfEntity) {
        this.isbn = isbn;
        this.userId = userId;
        this.UUIDOfEntity = UUIDOfEntity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUserId() {
        return userId;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }

    @Override
    public String toString() {
        return "AddBookReviewRequest{" +
                "isbn='" + isbn + '\'' +
                ", userId='" + userId + '\'' +
                ", UUIDOfEntity='" + UUIDOfEntity + '\'' +
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

        private String UUIDOfEntity;

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUUID(String UUIDOfEntity) {
            this.UUIDOfEntity = UUIDOfEntity;
            return this;
        }

        public AddBookReviewRequest build() {
            return new AddBookReviewRequest(isbn, userId, UUIDOfEntity);
        }
    }
}
