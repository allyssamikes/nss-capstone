package capstoneservice.activity.result;

import capstoneservice.models.BookModel;

public class GetBookResult {

    private final BookModel book;

    public GetBookResult(BookModel book) {
        this.book= book;
    }

    public BookModel getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "GetBookResult{" +
                "book=" + book +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BookModel book;

        public Builder withBook(BookModel book) {
            this.book = book;
            return this;
        }

        public GetBookResult build() {
            return new GetBookResult(book);
        }
    }
}
