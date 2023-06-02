package activity.request;

import dynamodb.models.GENRE;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetBookByGenreRequest {
    private final GENRE genre;

    public GetBookByGenreRequest(GENRE genre) {
        this.genre = genre;
    }

    public GENRE getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "GetBookByGenreRequest{" +
                "genre=" + genre +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }



    @JsonPOJOBuilder
    public static class Builder {
        private GENRE genre;


        public Builder withGenre(GENRE genre) {
            this.genre = genre;
            return this;
        }

        public GetBookByGenreRequest build() {
            return new GetBookByGenreRequest(genre);
        }
    }
}
