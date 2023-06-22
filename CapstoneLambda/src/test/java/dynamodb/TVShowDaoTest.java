package dynamodb;

import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.javaparser.utils.Utils.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TVShowDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;
    @InjectMocks
    private TVShowDao tvShowDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        tvShowDao = new TVShowDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getTVShow_withKeys_returnsTVShow() {
        // GIVEN
        String title = "Friends";
        String director = "JamesBurrows";
        when(dynamoDBMapper.load(TVShow.class, title, director)).thenReturn(new TVShow());

        // WHEN
        TVShow tvShow = tvShowDao.getTVShow(title, director);

        // THEN
        assertNotNull(tvShow);
        verify(dynamoDBMapper).load(TVShow.class, title,director);

    }
}
