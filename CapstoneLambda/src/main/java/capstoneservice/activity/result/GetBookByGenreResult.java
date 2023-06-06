package capstoneservice.activity.result;

import capstoneservice.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class GetBookByGenreResult {
    private final List<BookModel> models;

    public GetBookByGenreResult(List<BookModel> models) {
        this.models = models;
    }

    public List<BookModel> getModels() {
        return new ArrayList<>(models);
    }

    @Override
    public String toString() {
        return "GetBookByGenreResult{" +
                "models=" + models +
                '}';
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
        public GetBookByGenreResult build() {
            return new GetBookByGenreResult(models);
        }
    }
}
