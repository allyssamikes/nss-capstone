package activity.result;

import models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class RemoveBookFromToReadListResult {

    private final List<BookModel> models;


    public RemoveBookFromToReadListResult(List<BookModel> models) {
        this.models = models;
    }

    public List<BookModel> getModels() {
        return models;
    }

    @Override
    public String toString() {
        return "RemoveBookFromToReadListResult{" +
                "models=" + models +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<BookModel> models;

        public Builder withList(List<BookModel> list) {
            this.models= new ArrayList<>(list);
            return this;
        }

        public  RemoveBookFromToReadListResult build() {
            return new RemoveBookFromToReadListResult(models);
        }
    }
}
