package activity.request;

import dynamodb.models.Review;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

public class AddToWatchedListRequest {
    private final String title;
    private final  String director;
    private final List<Review> reviews;
    private final String userId;

    public AddToWatchedListRequest(String title, String director, List<Review> reviews, String userId) {
        this.title = title;
        this.director = director;
        this.reviews = reviews;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getUserId() {
        return userId;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        private String director;
        private List<Review> reviews;
        private String userId;


        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder withReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public AddToWatchedListRequest build() {
            return new AddToWatchedListRequest(title, director, reviews,userId);
        }
    }
}
