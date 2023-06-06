package activity;

import capstoneservice.activity.GetTVShowByStreamingServiceActivity;
import capstoneservice.activity.request.GetTVShowByStreamingServiceRequest;
import capstoneservice.activity.result.GetTVShowByStreamingServiceResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
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
        private GetTVShowByStreamingServiceActivity activity;

        @BeforeEach
        void setup() {
            openMocks(this);
            this.activity = new GetTVShowByStreamingServiceActivity(tvShowDao);
        }

        @Test
        void handleRequest_requestsATVShow_getsTVShow() {
            // GIVEN
            TVShow tvShow = new TVShow();
            tvShow.setTitle("Friends");
            tvShow.setStreamingService(STREAMING_SERVICE.NETFLIX);
            when(tvShowDao.getTVShow("Friends")).thenReturn(tvShow);
            TVShow show = new TVShow();
            show.setTitle("The Office");
            tvShow.setStreamingService(STREAMING_SERVICE.HULU);
            when(tvShowDao.getTVShow("The Office")).thenReturn(show);

            List<TVShow> shows = new ArrayList<>();
            shows.add(tvShow);
            when(tvShowDao.getTVShowByService(STREAMING_SERVICE.NETFLIX)).thenReturn(shows);

            List<Object> showModels = new ArrayList<>();
            showModels.add(new ModelConverter().toTVShowModel(tvShow));


            GetTVShowByStreamingServiceRequest request = GetTVShowByStreamingServiceRequest.builder()
                    .withService(STREAMING_SERVICE.NETFLIX)
                    .build();

            // WHEN
            GetTVShowByStreamingServiceResult result = activity.handleRequest(request);
            List<TVShowModel> models = result.getModels();

            // THEN
            assertEquals(showModels, models);
        }

}
