package activity;

import activity.request.AddToWatchedListRequest;
import activity.result.AddToWatchedListResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.TVShowDao;
import dynamodb.UserDao;
import dynamodb.models.Movie;
import dynamodb.models.User;
import models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class AddToWatchedListActivityTest {

    @Mock
    private UserDao userDao;

    @Mock
    private MovieDao movieDao;

    @Mock
    private TVShowDao tvShowDao;

    @InjectMocks
    private AddToWatchedListActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new AddToWatchedListActivity(userDao, movieDao, tvShowDao);
    }


    @Test
    void handleRequest_givenAMovie_updatesWatchedList() {
        // GIVEN
        User user = new User();
        user.setUserId("1234");
        when(userDao.getUser(anyString())).thenReturn(user);
        Movie movie = new Movie();
        movie.setTitle("The Parent Trap");
        movie.setDirector("Nancy Meyers");
        when(movieDao.getMovie(anyString(), any())).thenReturn(movie);

        List<Object> models = new ArrayList<>();
        MovieModel model = new ModelConverter().toMovieModel(movie);
        models.add(model);

        AddToWatchedListRequest request = AddToWatchedListRequest.builder()
                .withTitle("The Parent Trap")
                .withDirector("Nancy Meyers")
                .build();

        // WHEN
        AddToWatchedListResult result = activity.handleRequest(request);
        List<Object> movieModels = result.getModelsList();

        // THEN
        assertEquals(models, movieModels);
    }
}
