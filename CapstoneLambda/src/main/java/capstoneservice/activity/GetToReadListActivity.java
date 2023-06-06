package capstoneservice.activity;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetToReadListResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.BookModel;

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
