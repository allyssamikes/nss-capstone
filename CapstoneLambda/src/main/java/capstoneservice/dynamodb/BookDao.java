package capstoneservice.dynamodb;

import capstoneservice.exceptions.MovieNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.metrics.MetricsConstants;
import capstoneservice.metrics.MetricsPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;

import javax.inject.Inject;

import static capstoneservice.dynamodb.models.Book.AUTHOR_INDEX;

@Singleton
public class BookDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    @Inject
    public BookDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Book getBook(String isbn) {
        Book book = dynamoDbMapper.load(Book.class, isbn);
        if (null == book) {
            metricsPublisher.addCount(MetricsConstants.GETBOOK_BOOKNOTFOUND_COUNT, 1);
            throw new MovieNotFoundException();
        }
        metricsPublisher.addCount(MetricsConstants.GETBOOK_BOOKNOTFOUND_COUNT, 0);
        return book;
    }

    public Book saveBook(Book book) {
        this.dynamoDbMapper.save(book);
        return book;
    }

    public List<Book> getBooksByAuthor(String author) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":author", new AttributeValue().withS(author));
        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
                .withIndexName(AUTHOR_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("author = :author")
                .withExpressionAttributeValues(valueMap);

        return dynamoDbMapper.query(Book.class, queryExpression);
    }
}