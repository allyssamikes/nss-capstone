package capstoneservice.activity;

import capstoneservice.activity.request.GetTVShowRequest;
import capstoneservice.activity.result.GetTVShowResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.models.TVShowModel;

import javax.inject.Inject;

public class GetTVShowActivity {

    private final TVShowDao tvShowDao;

    @Inject
    public GetTVShowActivity(TVShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    public GetTVShowResult handleRequest(final GetTVShowRequest getTVShowRequest) {
        String title = getTVShowRequest.getTitle();
        String director = getTVShowRequest.getDirector();
        TVShow tvShow = tvShowDao.getTVShow(title, director);
        TVShowModel tvShowModel = new ModelConverter().toTVShowModel(tvShow);

        return GetTVShowResult.builder()
                .withTVShow(tvShowModel)
                .build();
    }
}
