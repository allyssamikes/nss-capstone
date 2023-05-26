package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
;import dynamodb.models.*;
import exceptions.TVShowNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;
import org.gradle.api.internal.attributes.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVShowDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    public static final String GENRE_INDEX = "Genre Index";

    public TVShowDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public TVShow getTVShow(String title) {
        TVShow tvShow = dynamoDbMapper.load(TVShow.class, title);
        if (null == tvShow) {
            metricsPublisher.addCount(MetricsConstants.GETTVSHOW_TVSHOWNOTFOUND_COUNT, 1);
            throw new TVShowNotFoundException();
            String.format("Could not find TVShow with title'%s'", title);
        }
        metricsPublisher.addCount(MetricsConstants.GETTVSHOW_TVSHOWNOTFOUND_COUNT, 0);
        return tvShow;
    }

    public TVShow saveTVShow(TVShow tvShow){
        this.dynamoDbMapper.save(tvShow);
        return tvShow;
    }

    public List<TVShow> getTVShowByService(STREAMING_SERVICE service) {


        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put("service", new AttributeValue().withS(service));

        DynamoDBQueryExpression<TVShow> queryExpression = new DynamoDBQueryExpression<TVShow>()
                .withHashConditionExpression("service = :service")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<TVShow> tvShowList = dynamoDbMapper.query(TVShow.class, queryExpression);

        if (null == tvShowList  || tvShowList.size() == 0) {
            throw new TVShowNotFoundException(
                    String.format("Could not find any TVShows with this service '%s'", service));
        }
        return tvShowList;
    }
}
