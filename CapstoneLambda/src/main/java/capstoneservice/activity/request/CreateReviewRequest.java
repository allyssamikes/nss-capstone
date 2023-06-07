package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateReviewRequest.Builder.class)
public class CreateReviewRequest {

    private final String userId;
    private final String review;
    private final Integer rating;
    private  final String UUIDOfEntity;

    public CreateReviewRequest(String userId, String review, Integer rating, String UUIDOfEntity) {
        this.userId = userId;
        this.review = review;
        this.rating = rating;
        this.UUIDOfEntity = UUIDOfEntity;
    }

    public String getUserId() {
        return userId;
    }

    public String getReview() {
        return review;
    }

    public Integer getRating() {
        return rating;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String review;
        private Integer rating;
        private String UUIDOfEntity;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withReview(String review) {
            this.review = review;
            return this;
        }

        public Builder withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder withUUID(String UUIDOfEntity) {
            this.UUIDOfEntity = UUIDOfEntity;
            return this;
        }

        public CreateReviewRequest build() {
            return new CreateReviewRequest(userId, review, rating, UUIDOfEntity);
        }
    }
}