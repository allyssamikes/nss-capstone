package capstoneservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import capstoneservice.utils.UniqueId;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName ="movies")
public class Movie {

    public static final String STREAMING_SERVICE_INDEX = "StreamingServiceIndex";
    private String title;
    private String director;
    private List<String> mainActors;
    private Integer lengthInMinutes;
    private String genre;
    private String streamingService;
    private List<Review> reviews;
    private UniqueId uniqueId = new UniqueId();

    private String UUIDAsString = uniqueId.generate();

    @DynamoDBHashKey(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBRangeKey(attributeName = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @DynamoDBAttribute(attributeName = "mainActors")
    public List<String> getMainActors() {
        return mainActors;
    }
    @DynamoDBAttribute(attributeName = "lengthInMinutes")
    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }


    @DynamoDBAttribute(attributeName = "genre")
    public String getGenre() {
        return genre;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {STREAMING_SERVICE_INDEX}, attributeName = "streamingService")
    public String getStreamingService() {
        return streamingService;
    }

    public void setStreamingService(String streamingService) {
        this.streamingService = streamingService;
    }

    @DynamoDBAttribute(attributeName = "reviews")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @DynamoDBAttribute(attributeName = "UUIDAsString")
    public String getUUIDAsString() {
        return UUIDAsString;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director);
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
                ", UUIDAsString='" + UUIDAsString + '\'' +
                '}';
    }
}
