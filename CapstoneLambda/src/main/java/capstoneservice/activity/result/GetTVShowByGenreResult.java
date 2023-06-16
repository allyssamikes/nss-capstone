package capstoneservice.activity.result;

import capstoneservice.models.TVShowModel;

import java.util.ArrayList;
import java.util.List;

public class GetTVShowByGenreResult {

    private final List<TVShowModel> models;

    public GetTVShowByGenreResult(List<TVShowModel> models) {
        this.models = models;
    }

    public List<TVShowModel> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetTVShowByGenreResult{" +
                "models=" + models +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TVShowModel> models;

        public Builder withList(List<TVShowModel> models) {
            this.models = new ArrayList<>(models);
            return this;
        }
        public GetTVShowByGenreResult build() {
            return new GetTVShowByGenreResult(models);
        }
    }
}
