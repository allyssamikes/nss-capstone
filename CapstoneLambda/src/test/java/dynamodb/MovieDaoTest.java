package dynamodb;

import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.models.Movie;
import capstoneservice.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;


import static com.github.javaparser.utils.Utils.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MovieDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;
    @InjectMocks
    private MovieDao movieDao;

    @BeforeEach
    public void setup() {
        initMocks(this);

        movieDao = new MovieDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getMovie_withKeys_returnsMovie() {
        // GIVEN
        String title = "TheParentTrap";
        String director = "NancyMeyers";
        when(dynamoDBMapper.load(Movie.class, title, director)).thenReturn(new Movie());

        // WHEN
        Movie movie = movieDao.getMovie(title,director);

        // THEN
        assertNotNull(movie);
        verify(dynamoDBMapper).load(Movie.class, title,director);

    }
}
