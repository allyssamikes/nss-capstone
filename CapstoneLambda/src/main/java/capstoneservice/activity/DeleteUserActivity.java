package capstoneservice.activity;

import capstoneservice.activity.request.DeleteUserRequest;
import capstoneservice.activity.result.DeleteUserResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.UserModel;

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
