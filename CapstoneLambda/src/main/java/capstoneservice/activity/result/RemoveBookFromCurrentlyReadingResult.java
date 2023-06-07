package capstoneservice.activity.result;

import capstoneservice.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class RemoveBookFromCurrentlyReadingResult {

    private final List<BookModel> models;


    public RemoveBookFromCurrentlyReadingResult(List<BookModel> models) {
        this.models = models;
    }

    public List<BookModel> getModels() {
        return models;
    }

    @Override
    public String toString() {
        return "RemoveBookFromCurrentlyReadingResult" +
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

        public  RemoveBookFromCurrentlyReadingResult build() {
            return new RemoveBookFromCurrentlyReadingResult(models);
        }}}
