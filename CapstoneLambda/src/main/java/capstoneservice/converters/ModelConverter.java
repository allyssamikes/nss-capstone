package capstoneservice.converters;

import capstoneservice.dynamodb.models.*;
import capstoneservice.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelConverter {


    public MovieModel toMovieModel(Movie movie) {
        return MovieModel.builder()
                .withTitle(movie.getTitle())
                .withDirector(movie.getDirector())
                .withMainActors(movie.getMainActors())
                .withLengthInMinutes(movie.getLengthInMinutes())
                .withGenre(movie.getGenre())
                .withStreamingService(movie.getStreamingService())
                .withReviews(movie.getReviews())
                .withUUIDAsString(movie.getUUIDAsString())
                .build();
    }

    public BookModel toBookModel(Book book) {
        return BookModel.builder()
                .withIsbn(book.getIsbn())
                .withTitle(book.getTitle())
                .withAuthor(book.getAuthor())
                .withYearPublished(book.getYearPublished())
                .withLengthInPages(book.getLengthInPages())
                .withGenre(book.getGenre())
                .withReviews(book.getReviews())
                .withUUIDAsString(book.getUUIDAsString())
                .build();
    }

    public TVShowModel toTVShowModel(TVShow tvshow) {
        return TVShowModel.builder()
                .withTitle(tvshow.getTitle())
                .withDirector(tvshow.getDirector())
                .withMainActors(tvshow.getMainActors())
                .withLengthInSeasons(tvshow.getLengthInSeasons())
                .withLengthInMinutes(tvshow.getLengthInMinutes())
                .withGenre(tvshow.getGenre())
                .withStreamingService(tvshow.getStreamingService())
                .withReviews(tvshow.getReviews())
                .withUUIDAsString(tvshow.getUUIDAsString())
                .build();
    }

    public ReviewModel toReviewModel(Review review) {
        return ReviewModel.builder()
                .withUserId(review.getUserId())
                .withReview(review.getReview())
                .withRating(review.getRating())
                .withUUIDOfEntity(review.getUUIDOfEntity())
                .build();
    }

    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withUserId(user.getUserId())
                .withName(user.getName())
                .withCurrentlyReadingList(user.getCurrentlyReading())
                .withCurrentlyWatchingList(user.getCurrentlyWatching())
                .withToReadList(user.getToReadList())
                .withToWatchList(user.getToWatchList())
                .withReadList(user.getReadList())
                .withWatchedList(user.getWatchedList())
                .build();
    }


    public List<BookModel> toBookModelList(List<Book> books) {
        List<BookModel> bookModels = new
                ArrayList<>();
        for (Book b : books){
            bookModels.add(toBookModel(b));
        }
        return bookModels;
    }

    public List<Object> toModelList(List<Object> watchList) {
        List<Object> models = new ArrayList<>();
        TVShow tvShow = new TVShow();

        for(Object o : watchList) {
            if(o.getClass().equals(tvShow.getClass())) {
                TVShow show ;
                show = (TVShow) o;
                TVShowModel tVModel = new ModelConverter().toTVShowModel(show);
                models.add(tVModel);
            } else {
                Movie theMovie;
                theMovie = (Movie) o;
                MovieModel movieModel = new ModelConverter().toMovieModel(theMovie);
                models.add(movieModel);
            }
        }

      return models;
    }

}
