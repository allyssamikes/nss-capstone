package dynamodb.models;

public class Review {

    private String userId;
    private String review;
    private Integer rating;
    private  String UUIDOfEntity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }

    public void setUUIDOfEntity(String UUIDOfEntity) {
        this.UUIDOfEntity = UUIDOfEntity;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId='" + userId + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", UUIDOfEntity='" + UUIDOfEntity + '\'' +
                '}';
    }
}
