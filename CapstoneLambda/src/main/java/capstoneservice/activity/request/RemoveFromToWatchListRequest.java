package capstoneservice.activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class RemoveFromToWatchListRequest {

    private final String userId;
    private final String title;
    private final String director;

    public RemoveFromToWatchListRequest(String userId, String title, String director) {
        this.userId = userId;
        this.title = title;
        this.director = director;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "RemoveFromToWatchListRequest{" +
                "userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder

       public static class Builder {
        private String userId;
        private String title;
        private String director;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public RemoveFromToWatchListRequest build() {
            return new RemoveFromToWatchListRequest(userId, title, director);
        }
    }
}
