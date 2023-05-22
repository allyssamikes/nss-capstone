package dynamodb.models;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private Integer  yearPublished;
    private Integer  lengthInPages;
    private GENRE  genre;
    private List<Review> reviews;
    private String UUID;

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

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getUUID() {
        return UUID;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", lengthInPages=" + lengthInPages +
                ", genre=" + genre +
                ", reviews=" + reviews +
                ", UUID='" + UUID + '\'' +
                '}';
    }

}
