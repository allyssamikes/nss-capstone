package activity;


import capstoneservice.activity.GetTVShowActivity;
import capstoneservice.activity.request.GetTVShowRequest;
import capstoneservice.activity.result.GetTVShowResult;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.TVShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetTVShowActivityTest {

    @Mock
    private TVShowDao tvShowDao;

    @InjectMocks
    private GetTVShowActivity getTVShowActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getTVShowActivity = new GetTVShowActivity(tvShowDao);
    }

    @Test
    public void handleRequest_savedBookFound_returnsBookModelResult() {
        // GIVEN
        String title = "Friends";
        String director = "JamesBurrows";

        TVShow tvShow = new TVShow();
        tvShow.setTitle(title);
        tvShow.setDirector(director);


        when(tvShowDao.getTVShow(title, director)).thenReturn(tvShow);

        GetTVShowRequest request = GetTVShowRequest.builder()
                .withTitle(title)
                .withDirector(director)
                .build();

        // WHEN
        GetTVShowResult result = getTVShowActivity.handleRequest(request);

        // THEN
        assertEquals(title, result.getTVShow().getTitle());
    }
}

