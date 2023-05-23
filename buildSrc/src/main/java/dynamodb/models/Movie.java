package dynamodb.models;

import java.util.List;

@DynamoDBTable(tableName = "movies")
public class Movie {

    private String title;
    private String director;
    private List<String> mainActors;
    private Integer lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
    private List<Review> reviews;
    private String UUID;

    @DynamoDBHashKey(attributeName = "title")
    public String getTitle() {
        return title;
    }

    @DynamoDBRangeKey(attributeName = "director")
    public String getDirector() {
        return director;
    }

    @DynamoDBAttribute(attributeName = "mainActors")
    public List<String> getMainActors() {
        return mainActors;
    }
    @DynamoDBAttribute(attributeName = "lengthInMinutes")
    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "genre")
    public GENRE getGenre() {
        return genre;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "streamingService")
    public STREAMING_SERVICE getStreamingService() {
        return streamingService;
    }

    public void setStreamingService(STREAMING_SERVICE streamingService) {
        this.streamingService = streamingService;
    }

    @DynamoDBAttribute(attributeName = "reviews")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @DynamoDBAttribute(attributeName = "UUID")
    public String getUUID() {
        return UUID;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", mainActors=" + mainActors +
                ", lengthInMinutes=" + lengthInMinutes +
                ", genre=" + genre +
                ", streamingService=" + streamingService +
                ", reviews=" + reviews +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
