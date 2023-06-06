package capstoneservice.dynamodb;

import capstoneservice.dynamodb.models.Movie;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import capstoneservice.exceptions.MovieNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import capstoneservice.dynamodb.models.*;
import capstoneservice.metrics.MetricsConstants;
import capstoneservice.metrics.MetricsPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class MovieDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    @Inject
    public MovieDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Movie getMovie(String title, String director) {
        Movie movie = dynamoDbMapper.load(Movie.class, title, director);
        if (null == movie) {
            metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 1);
            throw new MovieNotFoundException();
        }
        metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 0);
        return movie;
    }

    public Movie saveMovie(Movie movie){
        this.dynamoDbMapper.save(movie);
        return movie;
    }

    public List<Movie> getMovieByService(STREAMING_SERVICE sService) {
            String service = sService.toString();

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put("service", new AttributeValue().withS(service));

        DynamoDBQueryExpression<Movie> queryExpression = new DynamoDBQueryExpression<Movie>()
                .withKeyConditionExpression("service = :service")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<Movie> movieList = dynamoDbMapper.query(Movie.class, queryExpression);

        if (null == movieList  || movieList.size() == 0) {
            throw new MovieNotFoundException(
                    String.format("Could not find any Movies with this service '%s'", service));
        }
        return movieList;
    }
}
