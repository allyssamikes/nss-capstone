package capstoneservice.dynamodb;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.exceptions.TVShowNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import capstoneservice.dynamodb.models.*;
import capstoneservice.metrics.MetricsConstants;
import capstoneservice.metrics.MetricsPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.inject.Inject;

import static capstoneservice.dynamodb.models.TVShow.GENRE_INDEX;


@Singleton
public class TVShowDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;


    @Inject
    public TVShowDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public TVShow getTVShow(String title, String director) {
        TVShow tvShow = dynamoDbMapper.load(TVShow.class, title, director);
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

    public List<TVShow> getTVShowsByGenre(String genre) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":genre", new AttributeValue().withS(genre));
        DynamoDBQueryExpression<TVShow> queryExpression = new DynamoDBQueryExpression<TVShow>()
                .withIndexName(GENRE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("genre = :genre")
                .withExpressionAttributeValues(valueMap);

        return dynamoDbMapper.query(TVShow.class, queryExpression);
    }
}
