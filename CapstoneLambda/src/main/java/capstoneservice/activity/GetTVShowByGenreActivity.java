package capstoneservice.activity;

import capstoneservice.activity.request.GetTVShowByGenreRequest;
import capstoneservice.activity.result.GetTVShowByGenreResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.models.TVShowModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTVShowByGenreActivity {

    private final TVShowDao tvShowDao;

    @Inject
    public GetTVShowByGenreActivity(TVShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    public GetTVShowByGenreResult handleRequest(final GetTVShowByGenreRequest getTVShowByGenreRequest) {


        String genre = getTVShowByGenreRequest.getGenre();
        List<TVShow> tvShows = tvShowDao.getTVShowsByGenre(genre);

        List<TVShowModel> models = new ArrayList<>();
        for(TVShow t : tvShows) {
            TVShowModel model = new ModelConverter().toTVShowModel(t);
            models.add(model);
        }

        return GetTVShowByGenreResult.builder()
                .withList(models)
                .build();
    }
}
