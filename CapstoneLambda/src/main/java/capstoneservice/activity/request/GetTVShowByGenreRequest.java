package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = GetTVShowByGenreRequest.Builder.class)
public class GetTVShowByGenreRequest {

    private final String genre;
    public GetTVShowByGenreRequest(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "GetTVShowByGenreRequest{" +
                "genre=" + genre +
                '}';
    }
        //CHECKSTYLE:OFF:Builder

        public static Builder builder() {
            return new Builder();
        }

    @JsonPOJOBuilder
    public static class Builder {
    private String genre;

    public Builder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public GetTVShowByGenreRequest build() {
        return new GetTVShowByGenreRequest(genre);
    }
}
}
