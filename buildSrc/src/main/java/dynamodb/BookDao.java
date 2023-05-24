package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Book;
import exceptions.MovieNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

public class BookDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

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
}
