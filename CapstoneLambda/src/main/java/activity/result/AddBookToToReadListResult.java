package activity.result;
import models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class AddBookToToReadListResult {

    private final List<BookModel> bookModelsList;

    public AddBookToToReadListResult(List<BookModel> bookModelsList) {
        this.bookModelsList = bookModelsList;
    }
    public List<BookModel> getBookModelsList() {
        return bookModelsList;
    }

    @Override
    public String toString() {
        return "AddBookToToReadListResult{" +
                "bookModelsList=" + bookModelsList +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<BookModel> bookModelsList;
        public Builder withBooksList(List<BookModel> list) {
            this.bookModelsList = new ArrayList<>(list);
            return this;
        }
        public AddBookToToReadListResult build() {
            return new AddBookToToReadListResult(bookModelsList);
        }
    }
}
