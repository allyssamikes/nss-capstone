package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import utils.UniqueId;


import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName ="books")
public class Book {
    public static final String GENRE_INDEX = "Genre Index";
    private String isbn;
    private String title;
    private String author;

    private Integer  yearPublished;
    private Integer  lengthInPages;
    private GENRE genre;
    private List<Review> reviews;

    private UniqueId uniqueId = new UniqueId();

    private String UUIDAsString = uniqueId.generate();

    @DynamoDBHashKey(attributeName = "isbn")
    public String getIsbn() {
        return isbn;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    @DynamoDBAttribute(attributeName = "yearPublished")
    public Integer getYearPublished() {
        return yearPublished;
    }

    @DynamoDBAttribute(attributeName = "lengthInPages")
    public Integer getLengthInPages() {
        return lengthInPages;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {GENRE_INDEX}, attributeName = "genre")
    public GENRE getGenre() {
        return genre;
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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
