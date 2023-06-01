//package activity;
//
//import activity.request.AddBookReviewRequest;
//import activity.result.AddBookReviewResult;
//import converters.ModelConverter;
//import dynamodb.*;
//import dynamodb.models.Book;
//import dynamodb.models.Review;
//import models.BookModel;
//import models.ReviewModel;
//
//import org.junit.jupiter.api.BeforeEach;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.MockitoAnnotations.openMocks;
//import static org.mockito.ArgumentMatchers.any;
//import org.mockito.ArgumentCaptor;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddBookReviewActivityTest {
//
//    @Mock
//    private BookDao bookDao;
//
//    @Mock
//    private ReviewDao reviewDao;
//
//
//    @InjectMocks
//    private AddBookReviewActivity activity;
//
//    @BeforeEach
//    void setup() {
//        openMocks(this);
//        this.activity = new AddBookReviewActivity(bookDao, reviewDao);
//    }
//
//
//    @Test
//    void handleRequest_givenAReview_updatesReviewsList() {
//        // GIVEN
//        Book book = new Book();
//        book.setIsbn("abcd");
//        when(bookDao.getBook(anyString())).thenReturn(book);
//        Review review = new Review();
//        review.setUserId("1234");
//        review.setUUIDOfEntity("12234");
//        when(reviewDao.getReview(anyString(), any())).thenReturn(review);
//
//        List<ReviewModel> models = new ArrayList<>();
//        ReviewModel model = new ModelConverter().toReviewModel(review);
//        models.add(model);
//
//        AddBookReviewRequest request = AddBookReviewRequest.builder()
//                .withUserId("1234")
//                .withUUID("12234")
//                .build();
//
//        // WHEN
//        AddBookReviewResult result = activity.handleRequest(request);
//        List<ReviewModel> reviewModels = result.getReviewModelsList();
//
//        // THEN
//        assertEquals(models, reviewModels);
//    }
//}
