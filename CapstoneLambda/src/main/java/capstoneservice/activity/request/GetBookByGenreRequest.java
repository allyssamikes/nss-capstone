package capstoneservice.activity.request;

import capstoneservice.dynamodb.models.GENRE;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = GetBookByGenreRequest.Builder.class)
public class GetBookByGenreRequest {
    private final String genre;

    public GetBookByGenreRequest(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
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
        private String genre;


        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public GetBookByGenreRequest build() {
            return new GetBookByGenreRequest(genre);
        }
    }
}
