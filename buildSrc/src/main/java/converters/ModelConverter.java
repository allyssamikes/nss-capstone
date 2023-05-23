package converters;

import dynamodb.models.Book;
import dynamodb.models.Movie;
import dynamodb.models.TVShow;
import models.BookModel;
import models.MovieModel;
import models.TVShowModel;

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
                .withUUID(movie.getUUID())
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
                .withUUID(book.getUUID())
                .build();
    }

    public TVShowModel toTVShowModel(TVShow tvshow) {
        return TVShowModel.builder()
                .withTitle(tvshow.getTitle())
                .withMainActors(tvshow.getMainActors())
                .withLengthInSeasons(tvshow.getLengthInSeasons())
                .withLengthInMinutes(tvshow.getLengthInMinutes())
                .withGenre(tvshow.getGenre())
                .withStreamingService(tvshow.getStreamingService())
                .withReviews(tvshow.getReviews())
                .withUUID(tvshow.getUUID())
                .build();
    }
}
