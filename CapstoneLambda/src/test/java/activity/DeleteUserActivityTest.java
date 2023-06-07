package activity;

import capstoneservice.activity.DeleteUserActivity;
import capstoneservice.activity.request.DeleteUserRequest;
import capstoneservice.activity.result.DeleteUserResult;
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

public class DeleteUserActivityTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private DeleteUserActivity deleteUserActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        deleteUserActivity = new DeleteUserActivity(userDao);
    }

    @Test
    public void handleRequest_deleteUser_deletesUser() {
        // GIVEN
        String expectedUserId = "1234";

        DeleteUserRequest request = DeleteUserRequest.builder()
                .withUserId(expectedUserId)
                .build();

        // WHEN
        DeleteUserResult result = deleteUserActivity.handleRequest(request);

        // THEN
        verify(userDao).deleteUser(any(User.class));

        assertNull(userDao.getUser(expectedUserId));
    }
}