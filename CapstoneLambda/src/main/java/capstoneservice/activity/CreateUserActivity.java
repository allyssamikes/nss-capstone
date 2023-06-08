package capstoneservice.activity;

import capstoneservice.activity.request.CreateUserRequest;
import capstoneservice.activity.result.CreateUserResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public CreateUserActivity(UserDao userDao){
        this.userDao = userDao;
    };

    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest) {
        log.info("Received CreateUserRequest {}", createUserRequest);
        User user = new User();
        user.setUserId(createUserRequest.getUserId());
        user.setName(createUserRequest.getName());
        user.setToWatchList(createUserRequest.getToWatchList());
        user.setToReadList(createUserRequest.getToReadList());
        user.setCurrentlyReading(createUserRequest.getCurrentlyReading());
        user.setCurrentlyWatching(createUserRequest.getCurrentlyWatching());
        user.setWatchedList(createUserRequest.getWatchedList());
        user.setReadList(createUserRequest.getReadList());

         userDao.saveUser(user);

       UserModel model = new ModelConverter().toUserModel(user);
        return CreateUserResult.builder()
                .withModel(model)
                .build();
    }
}
