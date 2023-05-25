package activity;

import activity.request.CreateReviewRequest;
import activity.result.CreateReviewResult;
import converters.ModelConverter;
import dynamodb.ReviewDao;
import dynamodb.models.Review;
import models.ReviewModel;

import javax.inject.Inject;

public class CreateReviewActivity {

    private final ReviewDao reviewDao;

    @Inject
    public CreateReviewActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public CreateReviewResult handleRequest(final CreateReviewRequest createReviewRequest) {

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
