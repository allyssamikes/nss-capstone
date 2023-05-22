package dynamodb.models;

import java.util.List;

public class TVShow {

    private String title;
    private List<String> mainActors;
    private Integer  lengthInSeasons;
    private Integer  lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
    private List<Review> reviews;
    private String UUID;

    public String getTitle() {
        return title;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public Integer getLengthInSeasons() {
        return lengthInSeasons;
    }

    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    public GENRE getGenre() {
        return genre;
    }

    public STREAMING_SERVICE getStreamingService() {
        return streamingService;
    }

    public void setStreamingService(STREAMING_SERVICE streamingService) {
        this.streamingService = streamingService;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getUUID() {
        return UUID;
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "title='" + title + '\'' +
                ", mainActors=" + mainActors +
                ", lengthInSeasons=" + lengthInSeasons +
                ", lengthInMinutes=" + lengthInMinutes +
                ", genre=" + genre +
                ", streamingService=" + streamingService +
                ", reviews=" + reviews +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
