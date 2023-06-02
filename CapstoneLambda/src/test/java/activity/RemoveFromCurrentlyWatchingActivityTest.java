package activity;

import activity.request.RemoveFromCurrentlyWatchingRequest;
import activity.result.RemoveFromCurrentlyWatchingResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.TVShowDao;
import dynamodb.UserDao;
import dynamodb.models.TVShow;
import dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RemoveFromCurrentlyWatchingActivityTest {

    @Mock
    private UserDao userDao;

    @Mock
    private MovieDao movieDao;

    @Mock
    private TVShowDao tvShowDao;

    @InjectMocks
   private RemoveFromCurrentlyWatchingActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RemoveFromCurrentlyWatchingActivity(userDao, tvShowDao,movieDao);
    }

    @Test
    void handleRequest_removeFromList_updatesList() {
        // GIVEN
        User user = new User();
        user.setUserId("abcd");
        when(userDao.getUser("abcd")).thenReturn(user);
        TVShow tvShow = new TVShow();
        tvShow.setTitle("Friends");
        when(tvShowDao.getTVShow("Friends")).thenReturn(tvShow);
        TVShow show = new TVShow();
        show.setTitle("The Office");
        show.setDirector("Greg Daniels");
        when(tvShowDao.getTVShow("The Office")).thenReturn(show);
        Set<Object> shows = new HashSet<>();
        shows.add(tvShow);
        shows.add(show);
        user.setCurrentlyWatching(shows);

        List<Object> showModels = new ArrayList<>();
        showModels.add(new ModelConverter().toTVShowModel(tvShow));


        RemoveFromCurrentlyWatchingRequest request = RemoveFromCurrentlyWatchingRequest.builder()
                .withUserId("abcd")
                .withTitle("The Office")
                .withDirector("Greg Daniels")
                .build();


        // WHEN
        RemoveFromCurrentlyWatchingResult result = activity.handleRequest(request);
        List<Object> models = result.getModels();

        // THEN
        assertEquals(showModels, models);
    }
}
