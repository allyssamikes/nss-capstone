package capstoneservice.activity;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetCurrentlyWatchingResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;

import javax.inject.Inject;
import java.util.List;

public class GetCurrentlyWatchingActivity {
    private final UserDao userDao;
    @Inject
    public GetCurrentlyWatchingActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    public GetCurrentlyWatchingResult handleRequest(final GetListRequest getListRequest) {

        User user = userDao.getUser(getListRequest.getUserId());
        List<Object> models = new ModelConverter().toModelList(user.getCurrentlyWatching());

        return GetCurrentlyWatchingResult.builder()
                .withList(models)
                .build();
    }
}
