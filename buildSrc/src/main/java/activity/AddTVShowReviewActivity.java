package activity;

import activity.request.AddMovieReviewRequest;
import activity.request.AddTVShowReviewRequest;
import activity.result.AddMovieReviewResult;
import activity.result.AddTVShowReviewResult;
import converters.ModelConverter;
import dynamodb.ReviewDao;
import dynamodb.TVShowDao;
import dynamodb.models.Movie;
import dynamodb.models.Review;
import dynamodb.models.TVShow;
import exceptions.BookNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.TVShowNotFoundException;
import models.ReviewModel;

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
