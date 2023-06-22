package dynamodb;

import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.javaparser.utils.Utils.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookDaoTest {


    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;
    @InjectMocks
    private BookDao bookDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        bookDao = new BookDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getBook_withKey_returnsBook(){
        // GIVEN
        String isbn = "978-1-4328-5900-8";
        when(dynamoDBMapper.load(Book.class, isbn)).thenReturn(new Book());

        // WHEN
        Book book = bookDao.getBook(isbn);

        // THEN
        assertNotNull(book);
        verify(dynamoDBMapper).load(Book.class, isbn);

    }
}
