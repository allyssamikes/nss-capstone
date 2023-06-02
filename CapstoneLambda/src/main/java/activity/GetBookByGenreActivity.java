package activity;

import activity.request.GetBookByGenreRequest;
import activity.request.GetBookRequest;
import activity.result.GetBookByGenreResult;
import activity.result.GetBookResult;
import converters.ModelConverter;
import dynamodb.BookDao;
import dynamodb.models.Book;
import dynamodb.models.GENRE;
import models.BookModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetBookByGenreActivity {

    private final BookDao bookDao;

    @Inject
    public GetBookByGenreActivity(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public GetBookByGenreResult handleRequest(final GetBookByGenreRequest getBookByGenreRequest) {

        GENRE genre = getBookByGenreRequest.getGenre();
        List<Book> books = bookDao.getBooksByGenre(genre);

        List<BookModel> bookModels = new ArrayList<>();
        for(Book b : books) {
            BookModel model = new ModelConverter().toBookModel(b);
            bookModels.add(model);
        }

        return GetBookByGenreResult.builder()
                .withList(bookModels)
                .build();
    }
}
