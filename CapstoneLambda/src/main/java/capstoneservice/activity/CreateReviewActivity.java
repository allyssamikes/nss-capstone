package capstoneservice.activity;

import capstoneservice.activity.request.CreateReviewRequest;
import capstoneservice.activity.result.CreateReviewResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.ReviewDao;
import capstoneservice.dynamodb.models.Review;
import capstoneservice.models.ReviewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;

public class CreateReviewActivity {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;

    @Inject
    public CreateReviewActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public CreateReviewResult handleRequest(final CreateReviewRequest createReviewRequest) {
        log.info("Received CreateReviewRequest {}", createReviewRequest);

        Review newReview = new Review();
        newReview.setUserId(createReviewRequest.getUserId());
        newReview.setReview(createReviewRequest.getReview());
        newReview.setRating(createReviewRequest.getRating());
        newReview.setUUIDOfEntity(createReviewRequest.getUUIDOfEntity());

        reviewDao.saveReview(newReview);

        ReviewModel reviewModel = new ModelConverter().toReviewModel(newReview);
        return CreateReviewResult.builder()
                .withReview(reviewModel)
                .build();
    }
}
