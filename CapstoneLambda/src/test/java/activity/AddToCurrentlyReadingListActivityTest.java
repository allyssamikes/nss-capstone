package activity;

import capstoneservice.activity.AddBookToToReadListActivity;
import capstoneservice.activity.AddToCurrentlyReadingListActivity;
import capstoneservice.activity.request.AddBookToToReadListRequest;
import capstoneservice.activity.request.AddToCurrentlyReadingListRequest;
import capstoneservice.activity.result.AddBookToToReadListResult;
import capstoneservice.activity.result.AddToCurrentlyReadingListResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.dynamodb.models.User;
import capstoneservice.models.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddToCurrentlyReadingListActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private AddToCurrentlyReadingListActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new AddToCurrentlyReadingListActivity(userDao, bookDao);
    }

    @Test
    void handleRequest_addToList_updatesList() {
        // GIVEN
        User user = new User();
        user.setUserId("abcd");
        when(userDao.getUser("abcd")).thenReturn(user);
        Book book = new Book();
        book.setIsbn("978-1-4328-5900-8");
        when(bookDao.getBook("978-1-4328-5900-8")).thenReturn(book);
        Book book1 = new Book();
        book1.setIsbn("978-1-4328-5900-8");
        when(bookDao.getBook("978-1-4328-5900-8")).thenReturn(book1);

        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setCurrentlyReading(books);

        List<BookModel> bookModels = new ArrayList<>();
        bookModels.add(new ModelConverter().toBookModel(book));
        bookModels.add(new ModelConverter().toBookModel(book1));


        AddToCurrentlyReadingListRequest request = AddToCurrentlyReadingListRequest.builder()
                .withUserId("abcd")
                .withIsbn("978-1-4328-5900-8")
                .build();


        // WHEN
        AddToCurrentlyReadingListResult result = activity.handleRequest(request);
        List<BookModel> models = result.getBookModelsList();

        // THEN
        assertEquals(bookModels, models);
    }

}
