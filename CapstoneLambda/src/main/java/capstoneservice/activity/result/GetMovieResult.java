package capstoneservice.activity.result;

import capstoneservice.models.MovieModel;

public class GetMovieResult {

    private final MovieModel movie;

    public GetMovieResult(MovieModel movie) {
        this.movie = movie;
    }

    public MovieModel getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "GetMovieResult{" +
                "movie=" + movie +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private MovieModel movie;

        public Builder withMovie(MovieModel movie) {
            this.movie = movie;
            return this;
        }

        public GetMovieResult build() {
            return new GetMovieResult(movie);
        }
    }
}
