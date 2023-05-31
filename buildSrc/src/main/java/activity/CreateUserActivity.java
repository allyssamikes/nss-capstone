package activity;

import activity.request.CreateUserRequest;
import activity.result.CreateUserResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;
import models.UserModel;

import javax.inject.Inject;

public class CreateUserActivity {

    private final UserDao userDao;

    @Inject
    public CreateUserActivity(UserDao userDao){
        this.userDao = userDao;
    };

    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest) {

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
