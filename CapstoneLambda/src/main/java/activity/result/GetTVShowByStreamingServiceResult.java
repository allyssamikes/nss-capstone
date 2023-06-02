package activity.result;

import models.TVShowModel;

import java.util.ArrayList;
import java.util.List;

public class GetTVShowByStreamingServiceResult {

    private final List<TVShowModel> models;

    public GetTVShowByStreamingServiceResult(List<TVShowModel> models) {
        this.models = models;
    }

    public List<TVShowModel> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetTVShowByStreamingServiceResult{" +
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
        public GetTVShowByStreamingServiceResult build() {
            return new GetTVShowByStreamingServiceResult(models);
        }
    }
}
