package models;

import dynamodb.models.GENRE;
import dynamodb.models.Review;
import dynamodb.models.STREAMING_SERVICE;

import java.util.List;

public class TVShowModel {

    private String title;
    private List<String> mainActors;
    private Integer  lengthInSeasons;
    private Integer  lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
    private List<Review> reviews;
    private String UUID;

    public TVShowModel(String title, List<String> mainActors, Integer lengthInSeasons, Integer lengthInMinutes, GENRE genre, STREAMING_SERVICE streamingService, List<Review> reviews, String UUID) {
        this.title = title;
        this.mainActors = mainActors;
        this.lengthInSeasons = lengthInSeasons;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.streamingService = streamingService;
        this.reviews = reviews;
        this.UUID = UUID;
    }

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

    public List<Review> getReviews() {
        return reviews;
    }

    public String getUUID() {
        return UUID;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private List<String> mainActors;
        private Integer  lengthInSeasons;
        private Integer  lengthInMinutes;
        private GENRE genre;
        private STREAMING_SERVICE streamingService;
        private List<Review> reviews;
        private String UUID;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder withMainActors(List<String> mainActors) {
            this.mainActors = mainActors;
            return this;
        }

        public Builder withLengthInSeasons(Integer lengthInSeasons) {
            this.lengthInSeasons = lengthInSeasons;
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

        public Builder withUUID(String UUID) {
            this.UUID = UUID;
            return this;
        }
        public TVShowModel build() {
            return new TVShowModel(title, mainActors, lengthInSeasons, lengthInMinutes, genre, streamingService,  reviews,  UUID);
        }
    }
}
