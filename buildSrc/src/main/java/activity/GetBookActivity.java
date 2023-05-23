package activity;

import activity.request.GetBookRequest;
import activity.result.GetBookResult;
import converters.ModelConverter;
import dynamodb.BookDao;
import dynamodb.models.Book;
import models.BookModel;

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
