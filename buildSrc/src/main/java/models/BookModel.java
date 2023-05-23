package models;

import dynamodb.models.GENRE;
import dynamodb.models.Review;

import java.util.List;

public class BookModel {
    private String isbn;
    private String title;
    private String author;

    private Integer  yearPublished;
    private Integer  lengthInPages;
    private GENRE genre;
    private List<Review> reviews;
    private String UUID;

    public BookModel(String isbn, String title, String author, Integer yearPublished, Integer lengthInPages, GENRE genre, List<Review> reviews, String UUID) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.lengthInPages = lengthInPages;
        this.genre = genre;
        this.reviews = reviews;
        this.UUID = UUID;
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

    public String getUUID() {
        return UUID;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String isbn;
        private String title;
        private String author;

        private Integer  yearPublished;
        private Integer  lengthInPages;
        private GENRE genre;
        private List<Review> reviews;
        private String UUID;

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

        public Builder withUUID(String UUID) {
            this.UUID = UUID;
            return this;
        }

        public BookModel build() {
            return new BookModel(isbn, title, author, yearPublished, lengthInPages, genre, reviews, UUID);
        }
    }
}
