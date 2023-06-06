package capstoneservice.activity;

import capstoneservice.activity.request.RemoveFromCurrentlyWatchingRequest;
import capstoneservice.activity.result.RemoveFromCurrentlyWatchingResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.Movie;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.dynamodb.models.User;
import capstoneservice.exceptions.MovieNotFoundException;
import capstoneservice.exceptions.TVShowNotFoundException;
import capstoneservice.exceptions.UserNotFoundException;


import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveFromCurrentlyWatchingActivity {

    private final UserDao userDao;

    private final TVShowDao tvShowDao;
    private final MovieDao movieDao;


   @Inject
   public RemoveFromCurrentlyWatchingActivity(UserDao userDao, TVShowDao tvShowDao, MovieDao movieDao) {
        this.userDao = userDao;
        this.tvShowDao = tvShowDao;
        this.movieDao = movieDao;
    }


    public RemoveFromCurrentlyWatchingResult handleRequest(
            final RemoveFromCurrentlyWatchingRequest removeFromCurrentlyWatchingRequest) {

        String userId = removeFromCurrentlyWatchingRequest.getUserId();
        String title = removeFromCurrentlyWatchingRequest.getTitle();
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
            director = removeFromCurrentlyWatchingRequest.getDirector();
            movie = movieDao.getMovie(title, director);
        } catch (NullPointerException ex) {
            throw new MovieNotFoundException("This is a TV Show");
            // can I use Optionals here?
        }

        Set<Object> watchList;

        if (user.getCurrentlyWatching() == null) {
            throw new TVShowNotFoundException("This list is empty");
        } else  {
            watchList = new HashSet<>(user.getCurrentlyWatching());

        }
         if(watchList.contains(tvShow)) {
        watchList.remove(tvShow);
         }
         if (watchList.contains(movie)) {
             watchList.remove(movie);
         }
        user.setCurrentlyWatching(watchList);
        userDao.saveUser(user);

        List<Object> models = new ModelConverter().toModelList(user.getCurrentlyWatching());

        return RemoveFromCurrentlyWatchingResult.builder()
                .withList(models)
                .build();
    }
}
