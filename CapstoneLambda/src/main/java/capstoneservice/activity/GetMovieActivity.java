package capstoneservice.activity;

import capstoneservice.activity.request.GetMovieRequest;
import capstoneservice.activity.result.GetMovieResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.models.Movie;
import capstoneservice.models.MovieModel;

import javax.inject.Inject;

public class GetMovieActivity {
    private final MovieDao movieDao;

   @Inject
    public GetMovieActivity(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    public GetMovieResult handleRequest(final GetMovieRequest getMovieRequest) {
        String title = getMovieRequest.getTitle();
        String director = getMovieRequest.getDirector();
        Movie movie = movieDao.getMovie(title, director);
        MovieModel movieModel = new ModelConverter().toMovieModel(movie);

        return GetMovieResult.builder()
                .withMovie(movieModel)
                .build();
    }
}

