package activity.result;

import models.BookModel;
import models.MovieModel;

public class GetBookResult {

    private final BookModel book;

    public GetBookResult(BookModel book) {
        this.book= book;
    }

    public BookModel getBook() {
        return book;
    }


    //CHECKSTYLE:OFF:Builder
    public static GetBookResult.Builder builder() {
        return new GetBookResult.Builder();
    }

    @Override
    public String toString() {
        return "GetBookResult{" +
                "book=" + book +
                '}';
    }

    public static class Builder {
        private BookModel book;

        public GetBookResult.Builder withBook(BookModel book) {
            this.book = book;
            return this;
        }

        public GetBookResult build() {
            return new GetBookResult(book);
        }
    }
}
