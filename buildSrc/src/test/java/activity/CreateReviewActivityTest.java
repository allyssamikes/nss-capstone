package activity;

import activity.request.CreateReviewRequest;
import activity.result.CreateReviewResult;
import dynamodb.ReviewDao;
import dynamodb.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


public class CreateReviewActivityTest {

    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    private CreateReviewActivity createReviewActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createReviewActivity = new CreateReviewActivity(reviewDao);
    }

    @Test
    public void handleRequest_newReview_createsReview() {
        // GIVEN
        String expectedUserId = "1234";

        CreateReviewRequest request = CreateReviewRequest.builder()
                .withUserId(expectedUserId)
                .build();

        // WHEN
        CreateReviewResult result = createReviewActivity.handleRequest(request);

        // THEN
        verify(reviewDao).saveReview(any(Review.class));

        assertEquals(expectedUserId, result.getReview().getUserId());
    }
}
