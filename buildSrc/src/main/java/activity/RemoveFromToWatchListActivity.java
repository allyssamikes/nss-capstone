package activity;

import activity.request.RemoveFromCurrentlyWatchingRequest;
import activity.request.RemoveFromToWatchListRequest;
import activity.result.RemoveFromCurrentlyWatchingResult;
import activity.result.RemoveFromToWatchListResult;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveFromToWatchListActivity {

    private final UserDao userDao;

    private final TVShowDao tvShowDao;
    private final MovieDao movieDao;

    public RemoveFromToWatchListActivity(UserDao userDao, TVShowDao tvShowDao, MovieDao movieDao) {
        this.userDao = userDao;
        this.tvShowDao = tvShowDao;
        this.movieDao = movieDao;
    }

    public RemoveFromToWatchListResult handleRequest(
            final RemoveFromToWatchListRequest removeFromToWatchListRequest) {

        String userId = removeFromToWatchListRequest.getUserId();
        String title = removeFromToWatchListRequest.getTitle();
        User user;

        try {
            user= userDao.getUser(userId);
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
            director = removeFromToWatchListRequest.getDirector();
            movie = movieDao.getMovie(title, director);
        } catch (NullPointerException ex) {
            throw new MovieNotFoundException("This is a TV Show");
            // can I use Optionals here?
        }

        Set<Object> watchList;

        if (user.getCurrentlyWatching() == null) {
            throw new TVShowNotFoundException("This list is empty");
        } else  {
            watchList = new HashSet<>(user.getToWatchList());

        }
        if(watchList.contains(tvShow)) {
            watchList.remove(tvShow);
        }
        if (watchList.contains(movie)) {
            watchList.remove(movie);
        }
        user.setToWatchList(watchList);
        userDao.saveUser(user);

        List<Object> models = new ModelConverter().toModelList(user.getToWatchList());

        return RemoveFromToWatchListResult.builder()
                .withList(models)
                .build();
    }
}
