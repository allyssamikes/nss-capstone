package activity;


import capstoneservice.activity.GetMovieActivity;
import capstoneservice.activity.request.GetMovieRequest;
import capstoneservice.activity.result.GetMovieResult;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetMovieActivityTest {

    @Mock
    private MovieDao movieDao;

    @InjectMocks
    private GetMovieActivity getMovieActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getMovieActivity = new GetMovieActivity(movieDao);
    }

    @Test
    public void handleRequest_requestsMovie_returnsMovieModelResult() {
        // GIVEN
        String title = "TheParentTrap";
        String director = "NancyMeyers";

             Movie movie = new Movie();
          movie.setDirector(director);
          movie.setTitle(title);


        when(movieDao.getMovie(title, director)).thenReturn(movie);

        GetMovieRequest request = GetMovieRequest.builder()
                .withTitle(title)
                .withDirector(director)
                .build();

        // WHEN
        GetMovieResult result = getMovieActivity.handleRequest(request);

        // THEN
        assertEquals(title, result.getMovie().getTitle());
        assertEquals(director, result.getMovie().getDirector());
    }
}

