package capstoneservice.activity.result;


import java.util.ArrayList;
import java.util.List;

public class GetToWatchListResult {

    private final List<Object> models;

    public GetToWatchListResult(List<Object> models) {
        this.models = models;
    }

    public List<Object> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetToWatchListResult{" +
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
        public GetToWatchListResult build() {
            return new GetToWatchListResult(models);
        }
    }
}
