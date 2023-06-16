package capstoneservice.activity.request;

import capstoneservice.dynamodb.models.GENRE;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = GetTVShowByGenreRequest.Builder.class)
public class GetTVShowByGenreRequest {

    private final GENRE genre;
    public GetTVShowByGenreRequest(GENRE genre) {
        this.genre = genre;
    }

    public GENRE getGenre() {
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
    private GENRE genre;

    public Builder withGenre(GENRE genre) {
        this.genre = genre;
        return this;
    }

    public GetTVShowByGenreRequest build() {
        return new GetTVShowByGenreRequest(genre);
    }
}
}
