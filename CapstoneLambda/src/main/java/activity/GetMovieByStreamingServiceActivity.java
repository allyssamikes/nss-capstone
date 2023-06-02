package activity;

import activity.request.GetMovieByStreamingServiceRequest;
import activity.request.GetTVShowByStreamingServiceRequest;
import activity.result.GetMovieByStreamingServiceResult;
import activity.result.GetTVShowByStreamingServiceResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.TVShowDao;
import dynamodb.models.Movie;
import dynamodb.models.STREAMING_SERVICE;
import dynamodb.models.TVShow;
import models.MovieModel;
import models.TVShowModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetMovieByStreamingServiceActivity {

    private final MovieDao movieDao;

    @Inject
    public GetMovieByStreamingServiceActivity(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public GetMovieByStreamingServiceResult handleRequest(final GetMovieByStreamingServiceRequest getMovieByStreamingServiceRequest) {


        STREAMING_SERVICE service = getMovieByStreamingServiceRequest.getService();
        List<Movie> movies = movieDao.getMovieByService(service);

        List<MovieModel> models = new ArrayList<>();
        for(Movie m : movies) {
            MovieModel model = new ModelConverter().toMovieModel(m);
            models.add(model);
        }

        return GetMovieByStreamingServiceResult.builder()
                .withList(models)
                .build();
    }
}
