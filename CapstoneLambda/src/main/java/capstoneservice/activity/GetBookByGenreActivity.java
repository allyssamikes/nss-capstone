package capstoneservice.activity;

import capstoneservice.activity.request.GetBookByGenreRequest;
import capstoneservice.activity.result.GetBookByGenreResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.dynamodb.models.GENRE;
import capstoneservice.models.BookModel;

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

        String genre = getBookByGenreRequest.getGenre();
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
