package capstoneservice.models;


import java.util.Objects;

public class ReviewModel {

    private String userId;
    private String review;
    private String rating;
    private String UUIDOfEntity;


    public ReviewModel(String userId, String review, String rating, String UUIDOfEntity) {
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

    public String getRating() {
        return rating;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewModel that = (ReviewModel) o;
        return Objects.equals(userId, that.userId) && Objects.equals(UUIDOfEntity, that.UUIDOfEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, UUIDOfEntity);
    }

    public static class Builder {
        private String userId;
        private String review;
        private String rating;
        private String UUIDOfEntity;

    public Builder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Builder withReview(String review) {
        this.review = review;
        return this;
    }

    public Builder withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public Builder withUUID(String UUIDOfEntity) {
        this.UUIDOfEntity = UUIDOfEntity;
        return this;
    }

    public ReviewModel build() {
        return new ReviewModel(userId, review, rating, UUIDOfEntity);
    }
}

}
