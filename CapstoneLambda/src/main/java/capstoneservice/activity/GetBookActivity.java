package capstoneservice.activity;

import capstoneservice.activity.request.GetBookRequest;
import capstoneservice.activity.result.GetBookResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.models.BookModel;

import javax.inject.Inject;

public class GetBookActivity {

    private final BookDao bookDao;

    @Inject
    public GetBookActivity(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    public GetBookResult handleRequest(final GetBookRequest getBookRequest) {
        String isbn = getBookRequest.getIsbn();
        Book book= bookDao.getBook(isbn);
        BookModel bookModel = new ModelConverter().toBookModel(book);

        return GetBookResult.builder()
                .withBook(bookModel)
                .build();
    }
}
