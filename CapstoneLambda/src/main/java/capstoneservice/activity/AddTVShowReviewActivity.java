package capstoneservice.activity;

import capstoneservice.activity.request.AddTVShowReviewRequest;
import capstoneservice.activity.result.AddTVShowReviewResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.ReviewDao;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.Review;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.exceptions.BookNotFoundException;
import capstoneservice.exceptions.TVShowNotFoundException;
import capstoneservice.models.ReviewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AddTVShowReviewActivity {

    private final ReviewDao reviewDao;

    private final TVShowDao tvShowDao;

    @Inject
    public AddTVShowReviewActivity(ReviewDao reviewDao, TVShowDao tvShowDao) {
        this.reviewDao = reviewDao;
        this.tvShowDao = tvShowDao;
    }


    public AddTVShowReviewResult handleRequest(
            final AddTVShowReviewRequest addTVShowReviewRequest) {

        String title= addTVShowReviewRequest.getTitle();
        String userId = addTVShowReviewRequest.getUserId();
        String UUID = addTVShowReviewRequest.getUUIDOfEntity();

        Review review;
        try {
            review = reviewDao.getReview(userId, UUID);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }


        TVShow tvShow;
        try {
            tvShow = tvShowDao.getTVShow(title);
        } catch (NullPointerException ex) {
            throw new TVShowNotFoundException("TVShow is not in our database.");
        }

        List<Review> reviewList;
        if (tvShow.getReviews()== null) {
            reviewList = new ArrayList<>();
        } else  {
            reviewList = new ArrayList<>(tvShow.getReviews());
        }

        reviewList.add(review);
        tvShow.setReviews(reviewList);
        tvShowDao.saveTVShow(tvShow);


        List<ReviewModel> models = new ArrayList<>();
        for(Review r : reviewList) {
            ReviewModel model = new ModelConverter().toReviewModel(r);
            models.add(model);
        }

        return AddTVShowReviewResult.builder()
                .withReviewsList(models).build();
    }
    }
