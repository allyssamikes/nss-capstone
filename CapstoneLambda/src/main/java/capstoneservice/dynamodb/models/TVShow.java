package capstoneservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import capstoneservice.utils.UniqueId;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName ="tvshows")
public class TVShow {

    public static final String GENRE_INDEX = "Genre Index";
    private String title;

    private String director;
    private List<String> mainActors;
    private Integer  lengthInSeasons;
    private Integer  lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
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

    @DynamoDBAttribute(attributeName = "lengthInSeasons")
    public Integer getLengthInSeasons() {
        return lengthInSeasons;
    }

    @DynamoDBAttribute(attributeName = "lengthInMinutes")
    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {GENRE_INDEX}, attributeName = "genre")
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

    @DynamoDBAttribute(attributeName = "UUIDAsString")
    public String getUUIDAsString() {
        return UUIDAsString;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVShow tvShow = (TVShow) o;
        return Objects.equals(title, tvShow.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


    @Override
    public String toString() {
        return "TVShow{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", mainActors=" + mainActors +
                ", lengthInSeasons=" + lengthInSeasons +
                ", lengthInMinutes=" + lengthInMinutes +
                ", genre=" + genre +
                ", streamingService=" + streamingService +
                ", reviews=" + reviews +
                ", uniqueId=" + uniqueId +
                ", UUIDAsString='" + UUIDAsString + '\'' +
                '}';
    }
}
