//package activity;
//
//import activity.request.GetListRequest;
//import activity.result.GetToWatchListResult;
//import converters.ModelConverter;
//import dynamodb.UserDao;
//import dynamodb.models.TVShow;
//import dynamodb.models.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class GetToWatchListActivityTest {
//
//        @Mock
//        private UserDao userDao;
//
//
//        @InjectMocks
//        private GetToWatchListActivity activity;
//
//        @BeforeEach
//        void setup() {
//            openMocks(this);
//            this.activity = new GetToWatchListActivity(userDao);
//        }
//
//        @Test
//        void handleRequest_requestsList_getsList() {
//            // GIVEN
//            User user = new User();
//            user.setUserId("abcd");
//            TVShow tvShow = new TVShow();
//            tvShow.setTitle("Friends");
//            TVShow show = new TVShow();
//            show.setTitle("The Office");
//            Set<Object> shows = new HashSet<>();
//            shows.add(tvShow);
//            shows.add(show);
//            user.setCurrentlyWatching(shows);
//            when(userDao.getUser("abcd")).thenReturn(user);
//
//            List<Object> showModels = new ArrayList<>();
//            showModels.add(new ModelConverter().toTVShowModel(tvShow));
//            showModels.add(new ModelConverter().toTVShowModel(show));
//
//            GetListRequest request = GetListRequest.builder()
//                    .withUserId("abcd")
//                    .build();
//
//            // WHEN
//            GetToWatchListResult result = activity.handleRequest(request);
//            List<Object> models = result.getModels();
//
//            // THEN
//            assertEquals(showModels, models);
//        }
//    }
