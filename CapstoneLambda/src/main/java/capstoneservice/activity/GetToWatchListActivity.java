package capstoneservice.activity;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetToWatchListResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;

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
