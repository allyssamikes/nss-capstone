package capstoneservice.models;

import capstoneservice.dynamodb.models.GENRE;
import capstoneservice.dynamodb.models.Review;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;

import java.util.List;
import java.util.Objects;

public class MovieModel {
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieModel that = (MovieModel) o;
        return Objects.equals(title, that.title) && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director);
    }

    private String director;
    private List<String> mainActors;
    private Integer lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
    private List<Review> reviews;
    private String UUIDAsString;

    public MovieModel(String title, String director, List<String> mainActors, Integer lengthInMinutes, GENRE genre, STREAMING_SERVICE streamingService, List<Review> reviews, String UUIDAsString) {
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.streamingService = streamingService;
        this.reviews = reviews;
        this.UUIDAsString = UUIDAsString;
    }


    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getMainActors() {
        return mainActors;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public String getUUIDAsString() {
        return UUIDAsString;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String title;
        private String director;
        private List<String> mainActors;
        private Integer lengthInMinutes;
        private GENRE genre;
        private STREAMING_SERVICE streamingService;
        private List<Review> reviews;
        private String UUIDAsString;


    public Builder withTitle(String title) {
        this.title = title;
        return this;
    }

    public Builder withDirector(String director) {
        this.director = director;
        return this;
    }

    public Builder withMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
        return this;
    }

    public Builder withLengthInMinutes(Integer lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
        return this;
    }

    public Builder withGenre(GENRE genre) {
        this.genre = genre;
        return this;
    }

    public Builder withStreamingService(STREAMING_SERVICE streamingService) {
        this.streamingService = streamingService;
        return this;
    }

    public Builder withReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

        public Builder withUUIDAsString(String UUIDAsString) {
            this.UUIDAsString = UUIDAsString;
            return this;
        }

    public MovieModel build() {
        return new MovieModel(title, director, mainActors, lengthInMinutes, genre, streamingService, reviews, UUIDAsString);
    }

}}
