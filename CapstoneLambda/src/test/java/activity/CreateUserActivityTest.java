package activity;

import capstoneservice.activity.CreateUserActivity;
import capstoneservice.activity.request.CreateUserRequest;
import capstoneservice.activity.result.CreateUserResult;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class CreateUserActivityTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private CreateUserActivity createUserActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createUserActivity = new CreateUserActivity(userDao);
    }

    @Test
    public void handleRequest_newUser_createsUser() {
        // GIVEN
        String expectedUserId = "1234";

        CreateUserRequest request = CreateUserRequest.builder()
                .withUserId(expectedUserId)
                .build();

        // WHEN
        CreateUserResult result = createUserActivity.handleRequest(request);

        // THEN
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedUserId, result.getModel().getUserId());
    }
}
