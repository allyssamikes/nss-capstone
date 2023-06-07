package capstoneservice.activity.result;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentlyWatchingResult {

    private final List<Object> models;

    public GetCurrentlyWatchingResult(List<Object> models) {
        this.models = models;
    }

    public List<Object> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetCurrentlyWatchingResult{" +
                "models=" + models +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Object> models;

        public Builder withList(List<Object> models) {
            this.models = new ArrayList<>(models);
            return this;
        }
        public GetCurrentlyWatchingResult build() {
            return new GetCurrentlyWatchingResult(models);
        }
    }
}
