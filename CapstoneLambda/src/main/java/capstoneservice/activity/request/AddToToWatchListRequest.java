package capstoneservice.activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class AddToToWatchListRequest {

    private final String title;
    private final  String director;
    private final String userId;

    public AddToToWatchListRequest(String title, String director,String userId) {
        this.title = title;
        this.director = director;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
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
        private String userId;


        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public AddToToWatchListRequest build() {
            return new AddToToWatchListRequest(title, director,userId);
        }
    }
}
