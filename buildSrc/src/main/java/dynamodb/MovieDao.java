package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.*;
import exceptions.MovieNotFoundException;
import exceptions.TVShowNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;
import org.gradle.api.internal.attributes.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    public MovieDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Movie getMovie(String title, String director) {
        Movie movie = dynamoDbMapper.load(Movie.class, title, director);
        if (null == movie) {
            metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 1);
            throw new MovieNotFoundException();
                    String.format("Could not find Movie with title'%s' and director %s", title, director);
        }
        metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 0);
        return movie;
    }

    public Movie saveMovie(Movie movie){
        this.dynamoDbMapper.save(movie);
        return movie;
    }

    public List<Movie> getMovieByService(STREAMING_SERVICE service) {


        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put("service", new AttributeValue().withS(service));

        DynamoDBQueryExpression<Movie> queryExpression = new DynamoDBQueryExpression<Movie>()
                .withHashConditionExpression("service = :service")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<Movie> movieList = dynamoDbMapper.query(Movie.class, queryExpression);

        if (null == movieList  || movieList.size() == 0) {
            throw new MovieNotFoundException(
                    String.format("Could not find any Movies with this service '%s'", service));
        }
        return movieList;
    }
}
