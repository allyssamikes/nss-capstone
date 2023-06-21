package activity;

import capstoneservice.activity.GetReadListActivity;
import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetReadListResult;
import capstoneservice.converters.ModelConverter;
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

public class GetReadListActivityTest {

    @Mock
    private UserDao userDao;


    @InjectMocks
    private GetReadListActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new GetReadListActivity(userDao);
    }

    @Test
    void handleRequest_requestsList_getsList() {
        // GIVEN
        User user = new User();
        user.setUserId("abcd");
        Book book = new Book();
        book.setIsbn("978-1-4328-5900-8");
        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setReadList(books);
        when(userDao.getUser("abcd")).thenReturn(user);

        List<BookModel> bookModels = new ArrayList<>();
        bookModels.add(new ModelConverter().toBookModel(book));

        GetListRequest request = GetListRequest.builder()
                .withUserId("abcd")
                .build();

        // WHEN
        GetReadListResult result = activity.handleRequest(request);
        List<BookModel> models = result.getModels();

        // THEN
        assertEquals(bookModels, models);
    }
}


