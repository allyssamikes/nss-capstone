package capstoneservice.activity;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetReadListResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.BookModel;

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
