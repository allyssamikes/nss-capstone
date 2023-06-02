package activity;

import activity.request.GetListRequest;
import activity.result.GetCurrentlyReadingResult;
import converters.ModelConverter;
import dynamodb.UserDao;
import dynamodb.models.User;
import models.BookModel;

import javax.inject.Inject;
import java.util.List;

public class GetCurrentlyReadingActivity {
        private final UserDao userDao;
        @Inject
        public GetCurrentlyReadingActivity(UserDao userDao) {
            this.userDao = userDao;
        }
        public GetCurrentlyReadingResult handleRequest(final GetListRequest getListRequest) {

            User user = userDao.getUser(getListRequest.getUserId());
            List<BookModel> bookModels = new ModelConverter().toBookModelList(user.getCurrentlyReading());

            return GetCurrentlyReadingResult.builder()
                    .withList(bookModels)
                    .build();
        }
}
