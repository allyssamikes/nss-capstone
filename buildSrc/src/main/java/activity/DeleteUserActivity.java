package activity;

import activity.request.DeleteUserRequest;
import activity.result.CreateUserResult;
import activity.result.DeleteUserResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;
import models.UserModel;

import javax.inject.Inject;

public class DeleteUserActivity {
    private final UserDao userDao;

    @Inject
    public DeleteUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    public DeleteUserResult handleRequest(final DeleteUserRequest deleteUserRequest){
        User user = new User();
        user.setUserId(deleteUserRequest.getUserId());

        userDao.deleteUser(user);

        UserModel model = new ModelConverter().toUserModel(user);
        return DeleteUserResult.builder()
                .withModel(model)
                .build();
    }
}
