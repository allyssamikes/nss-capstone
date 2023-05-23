package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Movie;
import exceptions.MovieNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

public class MovieDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

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
}
