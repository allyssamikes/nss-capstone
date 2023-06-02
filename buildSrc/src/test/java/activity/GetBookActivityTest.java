package activity;

import activity.request.GetBookRequest;
import activity.result.GetBookResult;
import dynamodb.BookDao;
import dynamodb.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;


public class GetBookActivityTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private GetBookActivity getBookActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getBookActivity = new GetBookActivity(bookDao);
    }

    @Test
    public void handleRequest_savedBookFound_returnsBookModelResult() {
        // GIVEN
        String expectedISBN = "123456789";

        Book book = new Book();
        book.setIsbn(expectedISBN);


        when(bookDao.getBook(expectedISBN)).thenReturn(book);

        GetBookRequest request = GetBookRequest.builder()
                .withIsbn(expectedISBN)
                .build();

        // WHEN
        GetBookResult result = getBookActivity.handleRequest(request);

        // THEN
        assertEquals(expectedISBN, result.getBook().getIsbn());
    }
}
