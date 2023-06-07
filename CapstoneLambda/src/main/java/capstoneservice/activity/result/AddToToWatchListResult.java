package capstoneservice.activity.result;

import java.util.ArrayList;
import java.util.List;

public class AddToToWatchListResult {

    private final List<Object> modelsList;

    public AddToToWatchListResult(List<Object> modelsList) {
        this.modelsList = modelsList;
    }

    public List<Object> getModelsList() {
        return modelsList;
    }

    @Override
    public String toString() {
        return "AddToToWatchListResult{" +
                "modelsList=" + modelsList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Object> modelsList;
        public Builder withList(List<Object> list) {
            this.modelsList = new ArrayList<>(list);
            return this;
        }
        public AddToToWatchListResult build() {
            return new AddToToWatchListResult(modelsList);
        }
    }
}
