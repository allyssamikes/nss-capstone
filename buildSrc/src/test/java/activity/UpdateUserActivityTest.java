//package activity;
//
//import activity.request.UpdateUserRequest;
//import activity.result.UpdateUserResult;
//import dynamodb.UserDao;
//import dynamodb.models.User;
//
//public class UpdateUserActivityTest {
//
//    @Mock
//    private UserDao userDao;
//
//    @InjectMocks
//    private UpdateUserActivity updateUserActivity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        updateUserActivity = new UpdateUserActivity(userDao);
//    }
//
//    @Test
//    public void handleRequest_updateName_updatesUsersName() {
//        // GIVEN
//        String userId = "id";
//        String expectedName= "New Name";
//
//        UpdateUserRequest request = UpdateUserRequest.builder()
//                .withUserId(userId)
//                .withName(expectedName)
//                .build();
//
//        User original = new User();
//        original.setUserId(userId);
//        original.setName("Old Name");
//
//        when(userDao.getUser(userId)).thenReturn(original);
//
//        UpdateUserResult result = updateUserActivity.handleRequest(request);
//
//        // THEN
//        assertEquals(expectedName, result.getName());
//    }
//}
