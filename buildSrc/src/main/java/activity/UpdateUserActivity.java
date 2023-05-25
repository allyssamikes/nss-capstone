package activity;

import activity.request.UpdateUserRequest;
import activity.result.UpdateUserResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;

import javax.inject.Inject;

public class UpdateUserActivity {

    private final UserDao userDao;

   @Inject
    public UpdateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    public UpdateUserResult handleRequest(final UpdateUserRequest updateUserRequest) {

        User user = userDao.getUser(updateUserRequest.getUserId());

        user.setName(updateUserRequest.getName());
        userDao.saveUser(user);

        return UpdateUserResult.builder()
                .withUserModel(new ModelConverter().toUserModel(user))
                .build();
    }
}
