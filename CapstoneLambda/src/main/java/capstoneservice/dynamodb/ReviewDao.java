package capstoneservice.dynamodb;

import capstoneservice.dynamodb.models.Review;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import capstoneservice.exceptions.ReviewNotFoundException;
import capstoneservice.metrics.MetricsPublisher;

import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class ReviewDao {

    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

   @Inject
   public ReviewDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }


    public Review getReview(String userId, String UUID) {
        Review review = dynamoDbMapper.load(Review.class, userId, UUID);
        if (null == review) {
            throw new ReviewNotFoundException();
        }
        return review;
    }
    public Review saveReview(Review review){
        this.dynamoDbMapper.save(review);
        return review;
    }
    }
