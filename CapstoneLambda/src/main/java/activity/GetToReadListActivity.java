package activity;

import activity.request.GetListRequest;
import activity.result.GetToReadListResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;
import models.BookModel;

import javax.inject.Inject;
import java.util.List;

public class GetToReadListActivity {
    private final UserDao userDao;
    @Inject
    public GetToReadListActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    public GetToReadListResult handleRequest(final GetListRequest getListRequest) {

        User user = userDao.getUser(getListRequest.getUserId());
        List<BookModel> bookModels = new ModelConverter().toBookModelList(user.getToReadList());

        return GetToReadListResult.builder()
                .withList(bookModels)
                .build();
    }
}
