package capstoneservice.activity;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetCurrentlyReadingResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.BookModel;

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
