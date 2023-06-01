package activity;

import activity.request.CreateUserRequest;
import activity.result.CreateUserResult;
import dynamodb.UserDao;
import dynamodb.models.User;

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

        assertEquals(expectedUserId, result.getUser().getUserId());
    }
}
