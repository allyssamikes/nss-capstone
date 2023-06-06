package capstoneservice.activity.result;

import capstoneservice.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class GetMovieByStreamingServiceResult {

    private final List<MovieModel> models;

    public GetMovieByStreamingServiceResult(List<MovieModel> models) {
        this.models = models;
    }

    public List<MovieModel> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetMovieByStreamingServiceResult{" +
                "models=" + models +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<MovieModel> models;

        public Builder withList(List<MovieModel> models) {
            this.models = new ArrayList<>(models);
            return this;
        }
        public GetMovieByStreamingServiceResult build() {
            return new GetMovieByStreamingServiceResult(models);
        }
    }
}
