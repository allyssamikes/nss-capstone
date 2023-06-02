package activity;

import activity.request.GetListRequest;
import activity.result.GetToWatchListResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;

import javax.inject.Inject;
import java.util.List;

public class GetToWatchListActivity {

    private final UserDao userDao;
    @Inject
    public GetToWatchListActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    public GetToWatchListResult handleRequest(final GetListRequest getListRequest) {

        User user = userDao.getUser(getListRequest.getUserId());
        List<Object> models = new ModelConverter().toModelList(user.getToWatchList());

        return GetToWatchListResult.builder()
                .withList(models)
                .build();
    }
}
