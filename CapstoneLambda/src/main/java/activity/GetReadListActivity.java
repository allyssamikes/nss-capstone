package activity;

import activity.request.GetListRequest;
import activity.result.GetReadListResult;
import activity.result.GetToReadListResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;
import models.BookModel;

import javax.inject.Inject;
import java.util.List;

public class GetReadListActivity {

        private final UserDao userDao;

        @Inject
        public GetReadListActivity(UserDao userDao) {
            this.userDao = userDao;
        }

        public GetReadListResult handleRequest(final GetListRequest getListRequest) {

            User user = userDao.getUser(getListRequest.getUserId());
            List<BookModel> bookModels = new ModelConverter().toBookModelList(user.getReadList());

            return GetReadListResult.builder()
                    .withList(bookModels)
                    .build();
        }
    }
