package capstoneservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName ="reviews")
public class Review {

    private String userId;
    private String review;
    private String rating;
    private String UUIDOfEntity;


    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @DynamoDBRangeKey(attributeName = "UUIDOfEntity")
    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }

    public void setUUIDOfEntity(String UUIDOfEntity) {
        this.UUIDOfEntity = UUIDOfEntity;
    }

    @DynamoDBAttribute(attributeName = "review")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @DynamoDBAttribute(attributeName = "rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
