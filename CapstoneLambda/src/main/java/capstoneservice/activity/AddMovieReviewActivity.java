package capstoneservice.activity;

import capstoneservice.activity.request.AddMovieReviewRequest;
import capstoneservice.activity.result.AddMovieReviewResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.ReviewDao;
import capstoneservice.dynamodb.models.Movie;
import capstoneservice.dynamodb.models.Review;
import capstoneservice.exceptions.BookNotFoundException;
import capstoneservice.exceptions.MovieNotFoundException;
import capstoneservice.models.ReviewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AddMovieReviewActivity {

    private MovieDao movieDao;

    private ReviewDao reviewDao;

    @Inject
    public AddMovieReviewActivity(MovieDao movieDao, ReviewDao reviewDao) {
        this.movieDao = movieDao;
        this.reviewDao = reviewDao;
    }

    public AddMovieReviewResult handleRequest(
            final AddMovieReviewRequest addMovieReviewRequest) {

        String title= addMovieReviewRequest.getTitle();
        String director = addMovieReviewRequest.getDirector();
        String userId = addMovieReviewRequest.getUserId();
        String UUID = addMovieReviewRequest.getUUIDOfEntity();

        Review review;
        try {
            review = reviewDao.getReview(userId, UUID);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }


        Movie movie;
        try {
            movie = movieDao.getMovie(title, director);
        } catch (NullPointerException ex) {
            throw new MovieNotFoundException("Movie is not in our database.");
        }

        List<Review> reviewList;
        if (movie.getReviews()== null) {
            reviewList = new ArrayList<>();
        } else  {
            reviewList = new ArrayList<>(movie.getReviews());
        }

        reviewList.add(review);
        movie.setReviews(reviewList);
        movieDao.saveMovie(movie);


        List<ReviewModel> models = new ArrayList<>();
        for(Review r : reviewList) {
            ReviewModel model = new ModelConverter().toReviewModel(r);
            models.add(model);
        }

        return AddMovieReviewResult.builder()
                .withReviewsList(models).build();
    }

}
