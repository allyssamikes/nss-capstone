package capstoneservice.activity;

import capstoneservice.activity.request.AddToCurrentlyReadingListRequest;
import capstoneservice.activity.result.AddToCurrentlyReadingListResult;
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

public class AddToCurrentlyReadingListActivity {
    private final UserDao userDao;
    private final BookDao bookDao;

    @Inject
    public AddToCurrentlyReadingListActivity(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    public AddToCurrentlyReadingListResult handleRequest(
            final AddToCurrentlyReadingListRequest addToCurrentlyReadingListRequest) {

        String isbn= addToCurrentlyReadingListRequest.getIsbn();
        String userId = addToCurrentlyReadingListRequest.getUserId();


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

        if (theUser.getCurrentlyReading() == null) {
            readList = new ArrayList<>();
        } else  {
            readList = new ArrayList<>(theUser.getCurrentlyReading());
        }

        readList.add(book);
        theUser.setReadList(readList);
        userDao.saveUser(theUser);

        List<BookModel> bookModels = new ArrayList<>();
        for(Book b : readList) {
            BookModel model = new ModelConverter().toBookModel(b);
            bookModels.add(model);
        }

        return AddToCurrentlyReadingListResult.builder()
                .withBooksList(bookModels)
                .build();
    }
}
