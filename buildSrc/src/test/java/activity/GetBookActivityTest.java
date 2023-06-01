//package activity;
//
//import activity.request.GetBookRequest;
//import activity.result.GetBookResult;
//import dynamodb.BookDao;
//import dynamodb.models.Book;
//import org.gradle.internal.impldep.org.junit.Test;
//
//public class GetBookActivityTest {
//
//    @Mock
//    private BookDao bookDao;
//
//    private GetBookActivity getBookActivity;
//
//    @BeforeEach
//    public void setUp() {
//        initMocks(this);
//        getBookActivity = new GetBookActivity(bookDao);
//    }
//
//    @Test
//    public void handleRequest_savedBookFound_returnsBookModelResult() {
//        // GIVEN
//        String expectedISBN = "123456789";
//
//        Book book = new Book();
//        book.setIsbn(expectedISBN);
//
//
//        when(bookDao.getBook(expectedISBN)).thenReturn(book);
//
//        GetBookRequest request = GetBookRequest.builder()
//                .withIsbn(expectedISBN)
//                .build();
//
//        // WHEN
//        GetBookResult result = getBookActivity.handleRequest(request);
//
//        // THEN
//        assertEquals(expectedISBN, result.getBook().getIsbn());
//    }
//}
