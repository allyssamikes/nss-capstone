package activity;

import capstoneservice.activity.GetBookByAuthorActivity;
import capstoneservice.activity.request.GetBookByAuthorRequest;
import capstoneservice.activity.result.GetBookByAuthorResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.models.BookModel;
import capstoneservice.models.TVShowModel;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class GetBookByAuthorActivityTest {
        @Mock
        private BookDao bookDao;


        @InjectMocks
        private GetBookByAuthorActivity activity;

        @BeforeEach
        void setup() {
            openMocks(this);
            this.activity = new GetBookByAuthorActivity(bookDao);
        }

        @Test
        void handleRequest_requestsBookByAuthor_getsBooks() {
            // GIVEN
            Book book1 = new Book();
            book1.setIsbn("978-1-4328-5900-8");
            book1.setAuthor("MattHaig");
            List<Book> books = new ArrayList<>();
            books.add(book1);
            when(bookDao.getBooksByAuthor("MattHaig")).thenReturn(books);


            List<BookModel> bookModels = new ArrayList<>();
            bookModels.add(new ModelConverter().toBookModel(book1));


            GetBookByAuthorRequest request = GetBookByAuthorRequest.builder()
                    .withAuthor("MattHaig")
                    .build();

            // WHEN
            GetBookByAuthorResult result = activity.handleRequest(request);
            List<BookModel> models = result.getModels();

            // THEN
            assertEquals(bookModels, models);
        }

}
