package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Book;
import dynamodb.models.GENRE;
import dynamodb.models.Review;
import exceptions.MovieNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;
import org.gradle.api.internal.attributes.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    public BookDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Book getBook(String isbn) {
        Book book = dynamoDbMapper.load(Book.class, isbn);
        if (null == book) {
            metricsPublisher.addCount(MetricsConstants.GETBOOK_BOOKNOTFOUND_COUNT, 1);
            throw new MovieNotFoundException();
            String.format("Could not find Book with isbn'%s'", isbn);
        }
        metricsPublisher.addCount(MetricsConstants.GETBOOK_BOOKNOTFOUND_COUNT, 0);
        return book;
    }

    public Book saveBook(Book book){
        this.dynamoDbMapper.save(book);
        return book;
    }
public List<Book> getBooksByGenre(GENRE genre) {
    DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    Map<String, AttributeValue> valueMap = new HashMap<>();
    valueMap.put(":genre", new AttributeValue().withS(genre));
    DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
            .withIndexName(GENRE_INDEX)
            .withConsistentRead(false)
            .withKeyConditionExpression("genre = :genre")
            .withExpressionAttributeValues(valueMap);

    return mapper.query(Book.class, queryExpression);
}
}
