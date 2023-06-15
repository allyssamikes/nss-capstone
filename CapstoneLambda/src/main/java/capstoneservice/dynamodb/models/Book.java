package capstoneservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import capstoneservice.utils.UniqueId;


import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName ="books")
public class Book {
    public static final String AUTHOR_INDEX = "AuthorIndex";
    private String isbn;
    private String title;
    private String author;

    private Integer  yearPublished;
    private Integer  lengthInPages;
    private String genre;
    private List<Review> reviews;

    private UniqueId uniqueId = new UniqueId();

    private String UUIDAsString = uniqueId.generate();

    @DynamoDBHashKey(attributeName = "isbn")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {AUTHOR_INDEX}, attributeName = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {AUTHOR_INDEX}, attributeName = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBAttribute(attributeName = "yearPublished")
    public Integer getYearPublished() {
        return yearPublished;
    }
    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }


    @DynamoDBAttribute(attributeName = "lengthInPages")
    public Integer getLengthInPages() {
        return lengthInPages;
    }

    public void setLengthInPages(Integer lengthInPages) {
        this.lengthInPages = lengthInPages;
    }

    @DynamoDBAttribute(attributeName = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @DynamoDBAttribute(attributeName = "reviews")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

  @DynamoDBAttribute(attributeName = "UUIDAsString")
    public String getUUIDAsString() {
        return UUIDAsString;
    }

    public void setUUIDAsString(String UUIDAsString) {
        this.UUIDAsString = UUIDAsString;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
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
                ", UUIDAsString='" + UUIDAsString + '\'' +
                '}';
    }

}
