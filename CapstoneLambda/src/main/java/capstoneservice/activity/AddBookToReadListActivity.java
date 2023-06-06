package capstoneservice.activity;

import capstoneservice.activity.request.AddBookToReadListRequest;
import capstoneservice.activity.result.AddBookToReadListResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.BookDao;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.Book;
import capstoneservice.dynamodb.models.User;
import capstoneservice.exceptions.BookNotFoundException;
import capstoneservice.exceptions.UserNotFoundException;
import capstoneservice.models.BookModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddBookToReadListActivity {

    private final UserDao userDao;
    private final BookDao bookDao;

    @Inject
    public AddBookToReadListActivity(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    public AddBookToReadListResult handleRequest(
            final AddBookToReadListRequest addBookToReadListRequest) {

        String isbn= addBookToReadListRequest.getIsbn();
        String userId = addBookToReadListRequest.getUserId();


        User theUser;

        try {
            theUser= userDao.getUser(userId);
        } catch (NullPointerException ex) {
            throw new UserNotFoundException("User does not exist.");
        }

        Book book;
        try {
            book = bookDao.getBook(isbn);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }
        Set<Book> readList;

        if (theUser.getReadList() == null) {
            readList = new HashSet<>();
        } else  {
            readList = new HashSet<>(theUser.getReadList());
        }

        readList.add(book);
        theUser.setReadList(readList);
        userDao.saveUser(theUser);

        List<BookModel> bookModels = new ArrayList<>();
        for(Book b : readList) {
            BookModel model = new ModelConverter().toBookModel(b);
            bookModels.add(model);
        }

        return AddBookToReadListResult.builder()
                .withBooksList(bookModels)
                .build();
    }
}
