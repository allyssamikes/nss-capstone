package models;

import dynamodb.models.GENRE;
import dynamodb.models.Review;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookModel {
    private String isbn;
    private String title;
    private String author;

    private Integer  yearPublished;
    private Integer  lengthInPages;
    private GENRE genre;
    private List<Review> reviews;

    private String UUIDAsString;

    public BookModel(String isbn, String title, String author, Integer yearPublished, Integer lengthInPages, GENRE genre, List<Review> reviews, String UUIDAsString) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.lengthInPages = lengthInPages;
        this.genre = genre;
        this.reviews = reviews;
        this.UUIDAsString = UUIDAsString;
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public Integer getLengthInPages() {
        return lengthInPages;
    }

    public GENRE getGenre() {
        return genre;
    }

    public List<Review> getReviews() {
        return reviews;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public String getUUIDAsString() {
        return UUIDAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return Objects.equals(isbn, bookModel.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }


    public static class Builder {
        private String isbn;
        private String title;
        private String author;

        private Integer  yearPublished;
        private Integer  lengthInPages;
        private GENRE genre;
        private List<Review> reviews;
        private String UUIDAsString;

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withYearPublished(Integer yearPublished) {
            this.yearPublished = yearPublished;
            return this;
        }

        public Builder withLengthInPages(Integer lengthInPages) {
            this.lengthInPages = lengthInPages;
            return this;
        }

        public Builder withGenre(GENRE genre) {
            this.genre = genre;
            return this;
        }

        public Builder withReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder withUUIDAsString(String UUIDAsString) {
            this.UUIDAsString = UUIDAsString;
            return this;
        }
        public BookModel build() {
            return new BookModel(isbn, title, author, yearPublished, lengthInPages, genre, reviews, UUIDAsString);
        }
    }
}
