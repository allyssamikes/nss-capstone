package activity;

import capstoneservice.activity.GetTVShowByGenreActivity;
import capstoneservice.activity.request.GetTVShowByGenreRequest;
import capstoneservice.activity.result.GetTVShowByGenreResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.models.TVShowModel;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class GetTVShowByStreamingServiceActivityTest {
        @Mock
        private TVShowDao tvShowDao;


        @InjectMocks
        private GetTVShowByGenreActivity activity;

        @BeforeEach
        void setup() {
            openMocks(this);
            this.activity = new GetTVShowByGenreActivity(tvShowDao);
        }

        @Test
        void handleRequest_requestsATVShow_getsTVShow() {
            // GIVEN
            TVShow tvShow = new TVShow();
            tvShow.setTitle("Friends");
            tvShow.setDirector("James-Burrows");
            tvShow.setStreamingService("netflix");
            when(tvShowDao.getTVShow("Friends", "James-Burrows")).thenReturn(tvShow);
            TVShow show = new TVShow();
            show.setTitle("The Office");
            show.setDirector("Ken-Kwapis");
            tvShow.setGenre("comedy");
            when(tvShowDao.getTVShow("The Office", "Ken-Kwapis")).thenReturn(show);

            List<TVShow> shows = new ArrayList<>();
            shows.add(tvShow);
            when(tvShowDao.getTVShowsByGenre("comedy")).thenReturn(shows);

            List<Object> showModels = new ArrayList<>();
            showModels.add(new ModelConverter().toTVShowModel(tvShow));


            GetTVShowByGenreRequest request = GetTVShowByGenreRequest.builder()
                    .withGenre("comedy")
                    .build();

            // WHEN
            GetTVShowByGenreResult result = activity.handleRequest(request);
            List<TVShowModel> models = result.getModels();

            // THEN
            assertEquals(showModels, models);
        }

}
