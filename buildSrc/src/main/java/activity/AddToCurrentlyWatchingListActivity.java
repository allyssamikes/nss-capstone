package activity;

import activity.request.AddToCurrentlyWatchingListRequest;
import activity.result.AddToCurrentlyWatchingListResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.TVShowDao;
import dynamodb.UserDao;
import dynamodb.models.Movie;
import dynamodb.models.TVShow;
import dynamodb.models.User;
import exceptions.MovieNotFoundException;
import exceptions.TVShowNotFoundException;
import exceptions.UserNotFoundException;
import models.MovieModel;
import models.TVShowModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddToCurrentlyWatchingListActivity {

    private final UserDao userDao;
    private final MovieDao movieDao;
    private final TVShowDao tvShowDao;

    public AddToCurrentlyWatchingListActivity (UserDao userDao, MovieDao movieDao, TVShowDao tvShowDao) {
        this.userDao = userDao;
        this.movieDao = movieDao;
        this.tvShowDao = tvShowDao;
    }

    public AddToCurrentlyWatchingListResult handleRequest(
            final AddToCurrentlyWatchingListRequest addToCurrentlyWatchingListRequest) {

        String title =addToCurrentlyWatchingListRequest.getTitle();
        String userId = addToCurrentlyWatchingListRequest.getUserId();
        User theUser;

        try {
            theUser= userDao.getUser(userId);
        } catch (NullPointerException ex) {
            throw new UserNotFoundException("User does not exist.");
        }

        TVShow tvShow;
        try {
            tvShow = tvShowDao.getTVShow(title);
        } catch (NullPointerException ex) {
            throw new TVShowNotFoundException("TVShow is not in our database.");
        }

        Movie movie;
        String director;
        try {
            director = addToCurrentlyWatchingListRequest.getDirector();
            movie = movieDao.getMovie(title, director);
        } catch (NullPointerException ex) {
            throw new MovieNotFoundException("This is a TV Show");
            // can I use Optionals here?
        }

        Set<Object> watchList;

        if (theUser.getCurrentlyWatching()== null) {
            watchList = new HashSet<>();
        } else  {
            watchList = new HashSet<>(theUser.getCurrentlyWatching());
        }

        List<Object> models = new ArrayList<>();
        for(Object o : watchList) {
            if(o.getClass() == tvShow.getClass()) {
                TVShow show = (TVShow) o;
                watchList.add(show);
                TVShowModel tVModel = new ModelConverter().toTVShowModel(show);
                models.add(tVModel);
            } else {
                Movie theMovie = (Movie) o;
                watchList.add(theMovie);
                MovieModel movieModel = new ModelConverter().toMovieModel(theMovie);
                models.add(movieModel);
            }
        }
        theUser.setCurrentlyWatching(watchList);
        userDao.saveUser(theUser);

        return AddToCurrentlyWatchingListResult.builder()
                .withList(models)
                .build();
    }
}
