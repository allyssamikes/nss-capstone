package capstoneservice.activity.result;

import capstoneservice.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class GetBookByAuthorResult {
    private final List<BookModel> models;

    public GetBookByAuthorResult(List<BookModel> models) {
        this.models = models;
    }

    public List<BookModel> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetBookByAuthorResult{" +
                "models=" + models +
                "}";
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<BookModel> models;

        public Builder withList(List<BookModel> models) {
            this.models = new ArrayList<>(models);
            return this;
        }
        public GetBookByAuthorResult build() {
            return new GetBookByAuthorResult(models);
        }
    }
}
