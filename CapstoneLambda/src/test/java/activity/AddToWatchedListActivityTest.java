//package activity;
//
//
//import capstoneservice.activity.AddToWatchedListActivity;
//import capstoneservice.activity.request.AddToWatchedListRequest;
//import capstoneservice.activity.result.AddToWatchedListResult;
//import capstoneservice.converters.ModelConverter;
//import capstoneservice.dynamodb.MovieDao;
//import capstoneservice.dynamodb.TVShowDao;
//import capstoneservice.dynamodb.UserDao;
//import capstoneservice.dynamodb.models.Movie;
//import capstoneservice.dynamodb.models.User;
//import capstoneservice.models.MovieModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.MockitoAnnotations.openMocks;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class AddToWatchedListActivityTest {
//
//    @Mock
//    private UserDao userDao;
//
//    @Mock
//    private MovieDao movieDao;
//
//    @Mock
//    private TVShowDao tvShowDao;
//
//    @InjectMocks
//    private AddToWatchedListActivity activity;
//
//    @BeforeEach
//    void setup() {
//        openMocks(this);
//        this.activity = new AddToWatchedListActivity(userDao, movieDao, tvShowDao);
//    }
//
//
//    @Test
//    void handleRequest_givenAMovie_updatesWatchedList() {
//        // GIVEN
//        User user = new User();
//        user.setUserId("1234");
//        Set<Object> list = new HashSet();
//        user.setWatchedList(list);
//        when(userDao.getUser("1234")).thenReturn(user);
//        Movie movie = new Movie();
//        movie.setTitle("The Parent Trap");
//        movie.setDirector("Nancy Meyers");
//        when(movieDao.getMovie("The Parent Trap", "Nancy Meyers")).thenReturn(movie);
//
//        List<Object> models = new ArrayList<>();
//        MovieModel model = new ModelConverter().toMovieModel(movie);
//        models.add(model);
//
//        AddToWatchedListRequest request = AddToWatchedListRequest.builder()
//                .withTitle("The Parent Trap")
//                .withDirector("Nancy Meyers")
//                .withUserId("1234")
//                .build();
//
//        // WHEN
//        AddToWatchedListResult result = activity.handleRequest(request);
//        List<Object> movieModels = result.getModelsList();
//
//        // THEN
//        assertEquals(models, movieModels);
//    }
//}
