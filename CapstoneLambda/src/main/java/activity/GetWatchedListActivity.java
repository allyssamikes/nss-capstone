package activity;

import activity.request.GetListRequest;
import activity.result.GetWatchedListResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;

import javax.inject.Inject;
import java.util.List;

public class GetWatchedListActivity {

    private final UserDao userDao;
    @Inject
    public GetWatchedListActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    public GetWatchedListResult handleRequest(final GetListRequest getListRequest) {

        User user = userDao.getUser(getListRequest.getUserId());
        List<Object> models = new ModelConverter().toModelList(user.getWatchedList());

        return GetWatchedListResult.builder()
                .withList(models)
                .build();
    }
}
