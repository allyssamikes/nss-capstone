package activity;

import activity.request.AddBookReviewRequest;
import activity.result.AddBookReviewResult;
import converters.ModelConverter;
import dynamodb.BookDao;
import dynamodb.ReviewDao;
import dynamodb.models.Book;
import dynamodb.models.Review;
import exceptions.BookNotFoundException;
import models.ReviewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AddBookReviewActivity {

    private final BookDao bookDao;

    private final ReviewDao reviewDao;


  @Inject
  public AddBookReviewActivity(BookDao bookDao, ReviewDao reviewDao) {
        this.bookDao = bookDao;
        this.reviewDao = reviewDao;
    }

    public AddBookReviewResult handleRequest(
            final AddBookReviewRequest addBookReviewRequest) {

        String isbn= addBookReviewRequest.getIsbn();
        String userId = addBookReviewRequest.getUserId();
        String UUID = addBookReviewRequest.getUUIDOfEntity();

        Review review;
        try {
            review = reviewDao.getReview(userId, UUID);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }


        Book book;
        try {
            book = bookDao.getBook(isbn);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }

      List<Review> reviewList;
        if (book.getReviews()== null) {
            reviewList = new ArrayList<>();
        } else  {
            reviewList = new ArrayList<>(book.getReviews());
        }

        reviewList.add(review);
        book.setReviews(reviewList);
        bookDao.saveBook(book);


        List<ReviewModel> models = new ArrayList<>();
        for(Review r : reviewList) {
            ReviewModel model = new ModelConverter().toReviewModel(r);
            models.add(model);
        }

        return AddBookReviewResult.builder()
                .withReviewsList(models).build();
    }
}
