package capstoneservice.dynamodb;

import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.exceptions.TVShowNotFoundException;
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
public class TVShowDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    @Inject
    public TVShowDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public TVShow getTVShow(String title) {
        TVShow tvShow = dynamoDbMapper.load(TVShow.class, title);
        if (null == tvShow) {
            metricsPublisher.addCount(MetricsConstants.GETTVSHOW_TVSHOWNOTFOUND_COUNT, 1);
            throw new TVShowNotFoundException();
        }
        metricsPublisher.addCount(MetricsConstants.GETTVSHOW_TVSHOWNOTFOUND_COUNT, 0);
        return tvShow;
    }

    public TVShow saveTVShow(TVShow tvShow){
        this.dynamoDbMapper.save(tvShow);
        return tvShow;
    }

    public List<TVShow> getTVShowByService(STREAMING_SERVICE sService) {
        String service = sService.toString();

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put("service", new AttributeValue().withS(service));

        DynamoDBQueryExpression<TVShow> queryExpression = new DynamoDBQueryExpression<TVShow>()
                .withKeyConditionExpression("service = :service")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<TVShow> tvShowList = dynamoDbMapper.query(TVShow.class, queryExpression);

        if (null == tvShowList  || tvShowList.size() == 0) {
            throw new TVShowNotFoundException(
                    String.format("Could not find any TVShows with this service '%s'", service));
        }
        return tvShowList;
    }
}
