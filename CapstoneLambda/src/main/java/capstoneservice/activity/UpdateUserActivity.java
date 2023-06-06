package capstoneservice.activity;

import capstoneservice.activity.request.UpdateUserRequest;
import capstoneservice.activity.result.UpdateUserResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;

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
