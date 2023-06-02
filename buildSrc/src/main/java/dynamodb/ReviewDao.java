package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Review;
import dynamodb.models.User;
import exceptions.ReviewNotFoundException;
import exceptions.UserNotFoundException;
import metrics.MetricsPublisher;

import java.util.Map;
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
