package capstoneservice.activity;

import capstoneservice.activity.request.AddToWatchedListRequest;
import capstoneservice.activity.result.AddToWatchedListResult;
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
import capstoneservice.models.MovieModel;
import capstoneservice.models.TVShowModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class AddToWatchedListActivity {

    private final UserDao userDao;
    private final MovieDao movieDao;
    private final TVShowDao tvShowDao;


   @Inject
   public AddToWatchedListActivity(UserDao userDao, MovieDao movieDao, TVShowDao tvShowDao) {
        this.userDao = userDao;
        this.movieDao = movieDao;
        this.tvShowDao = tvShowDao;
    }

    public AddToWatchedListResult handleRequest(
            final AddToWatchedListRequest addToWatchedListRequest) {

        String title = addToWatchedListRequest.getTitle();
        String director  = addToWatchedListRequest.getDirector();
        String userId = addToWatchedListRequest.getUserId();
        User theUser;

        try {
            theUser= userDao.getUser(userId);
        } catch (NullPointerException ex) {
            throw new UserNotFoundException("User does not exist.");
        }

        TVShow tvShow = null;
        Movie movie;
        try {
            tvShow = tvShowDao.getTVShow(title, director);
        } catch (NullPointerException ex) {
            movie = movieDao.getMovie(title, director);
        }

//        Movie movie;
//        try {
//            movie = movieDao.getMovie(title, director);
//        } catch (NullPointerException ex) {
//            throw new MovieNotFoundException("This is a TV Show");
//            // can I use Optionals here?
//        }

        List<Object> watchList;

        if (theUser.getWatchedList() == null) {
            watchList = new ArrayList<>();
        } else  {
            watchList = new ArrayList<>(theUser.getWatchedList());
        }


        List<Object> models = new ArrayList<>();
        for(Object o : watchList) {
            if(o.getClass().equals(tvShow.getClass())) {
                tvShow = (TVShow) o;
                watchList.add(tvShow);
                TVShowModel tVModel = new ModelConverter().toTVShowModel(tvShow);
                models.add(tVModel);
            } else {
                movie = (Movie) o;
                watchList.add(movie);
                MovieModel movieModel = new ModelConverter().toMovieModel(movie);
                models.add(movieModel);
            }
        }
        theUser.setToWatchList(watchList);
        userDao.saveUser(theUser);

        return AddToWatchedListResult.builder()
                .withList(models)
                .build();
    }

}
