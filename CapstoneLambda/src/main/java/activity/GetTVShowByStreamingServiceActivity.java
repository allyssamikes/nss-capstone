package activity;

import activity.request.GetTVShowByStreamingServiceRequest;
import activity.result.GetTVShowByStreamingServiceResult;
import converters.ModelConverter;
import dynamodb.TVShowDao;
import dynamodb.models.STREAMING_SERVICE;
import dynamodb.models.TVShow;
import models.TVShowModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTVShowByStreamingServiceActivity {

    private final TVShowDao tvShowDao;

    @Inject
    public GetTVShowByStreamingServiceActivity(TVShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    public GetTVShowByStreamingServiceResult handleRequest(final GetTVShowByStreamingServiceRequest getTVShowByStreamingServiceRequest) {


        STREAMING_SERVICE service = getTVShowByStreamingServiceRequest.getService();
        List<TVShow> tvShows = tvShowDao.getTVShowByService(service);

        List<TVShowModel> models = new ArrayList<>();
        for(TVShow t : tvShows) {
            TVShowModel model = new ModelConverter().toTVShowModel(t);
            models.add(model);
        }

        return GetTVShowByStreamingServiceResult.builder()
                .withList(models)
                .build();
    }
}
