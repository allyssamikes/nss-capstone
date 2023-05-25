package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
;import dynamodb.models.TVShow;
import exceptions.TVShowNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

public class TVShowDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

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
}
