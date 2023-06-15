package capstoneservice.activity;

import capstoneservice.activity.request.GetBookByAuthorRequest;
import capstoneservice.activity.result.GetBookByAuthorResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.models.BookModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetBookByAuthorActivity {

    private final BookDao bookDao;

    @Inject
    public GetBookByAuthorActivity(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public GetBookByAuthorResult handleRequest(final GetBookByAuthorRequest getBookByAuthorRequest) {

        String author = getBookByAuthorRequest.getAuthor();
        List<Book> books = bookDao.getBooksByAuthor(author);

        List<BookModel> bookModels = new ArrayList<>();
        for(Book b : books) {
            BookModel model = new ModelConverter().toBookModel(b);
            bookModels.add(model);
        }

        return GetBookByAuthorResult.builder()
                .withList(bookModels)
                .build();
    }
}
