package converters;

import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.models.BookModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {
    private ModelConverter converter = new ModelConverter();


    @Test
    void toBookModel_convertsBook() {
        Book book = new Book();
        book.setIsbn("978-1-4328-5900-8");
        book.setTitle("NinePerfectStrangers");
        book.setAuthor("LianeMoriarty");

        BookModel bookModel = converter.toBookModel(book);

        assertEquals(book.getTitle(), bookModel.getTitle());
        assertEquals(book.getIsbn(), bookModel.getIsbn());
        assertEquals(book.getAuthor(), bookModel.getAuthor());
    }
}
