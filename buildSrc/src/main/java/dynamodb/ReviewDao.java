package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Review;
import metrics.MetricsPublisher;

public class ReviewDao {

    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    public ReviewDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Review saveReview(Review review){
        this.dynamoDbMapper.save(review);
        return review;
    }
    }
