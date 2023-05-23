package activity;

import activity.request.GetMovieRequest;
import activity.result.GetMovieResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.models.Movie;
import models.MovieModel;

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

