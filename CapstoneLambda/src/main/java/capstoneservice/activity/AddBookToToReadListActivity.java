package capstoneservice.activity;

import capstoneservice.activity.request.AddBookToToReadListRequest;
import capstoneservice.activity.result.AddBookToToReadListResult;
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
import java.util.List;

public class AddBookToToReadListActivity {

    private final UserDao userDao;
    private final BookDao bookDao;

    @Inject
    public AddBookToToReadListActivity(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    public AddBookToToReadListResult handleRequest(
            final AddBookToToReadListRequest addBookToToReadListRequest) {

        String isbn= addBookToToReadListRequest.getIsbn();
        String userId = addBookToToReadListRequest.getUserId();


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
        List<Book> readList;

        if (theUser.getToReadList() == null) {
            readList = new ArrayList<>();
        } else  {
            readList = new ArrayList<>(theUser.getToReadList());
        }

        readList.add(book);
        theUser.setToReadList(readList);
        userDao.saveUser(theUser);

        List<BookModel> bookModels = new ArrayList<>();
        for(Book b : readList) {
            BookModel model = new ModelConverter().toBookModel(b);
            bookModels.add(model);
        }

        return AddBookToToReadListResult.builder()
                .withBooksList(bookModels)
                .build();
    }
}

