package capstoneservice.activity;

import capstoneservice.activity.request.GetMovieByStreamingServiceRequest;
import capstoneservice.activity.result.GetMovieByStreamingServiceResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.models.Movie;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import capstoneservice.models.MovieModel;

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
