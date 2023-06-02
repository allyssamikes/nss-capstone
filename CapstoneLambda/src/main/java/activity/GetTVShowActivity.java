package activity;

import activity.request.GetBookRequest;
import activity.request.GetTVShowRequest;
import activity.result.GetBookResult;
import activity.result.GetTVShowResult;
import converters.ModelConverter;
import dynamodb.TVShowDao;
import dynamodb.models.Book;
import dynamodb.models.TVShow;
import models.BookModel;
import models.TVShowModel;

import javax.inject.Inject;

public class GetTVShowActivity {

    private final TVShowDao tvShowDao;

    @Inject
    public GetTVShowActivity(TVShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    public GetTVShowResult handleRequest(final GetTVShowRequest getTVShowRequest) {
        String title = getTVShowRequest.getTitle();
        TVShow tvShow = tvShowDao.getTVShow(title);
        TVShowModel tvShowModel = new ModelConverter().toTVShowModel(tvShow);

        return GetTVShowResult.builder()
                .withTVShow(tvShowModel)
                .build();
    }
}
