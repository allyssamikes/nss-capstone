package activity;

import activity.request.RemoveBookFromToReadListRequest;
import activity.result.RemoveBookFromToReadListResult;
import converters.ModelConverter;
import dynamodb.BookDao;
import dynamodb.UserDao;
import dynamodb.models.Book;
import dynamodb.models.User;
import exceptions.BookNotFoundException;
import exceptions.UserNotFoundException;
import models.BookModel;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveBookFromToReadListActivity {

    private final BookDao bookDao;
    private final UserDao userDao;


    @Inject
    public RemoveBookFromToReadListActivity(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public RemoveBookFromToReadListResult handleRequest(
            final RemoveBookFromToReadListRequest removeBookFromToReadListRequest) {

      String isbn = removeBookFromToReadListRequest.getIsbn();
        String userId = removeBookFromToReadListRequest.getUserId();

     User user;

        try
        {user = userDao.getUser(userId);
        } catch (NullPointerException ex) {
            throw new UserNotFoundException("User is not in our database.");
        }

       Book book;

        try {
        book = bookDao.getBook(isbn);
        } catch (NullPointerException ex) {
            throw new BookNotFoundException("Book is not in our database.");
        }
        Set<Book> bookList;

        if (user.getToReadList() == null) {
            throw new BookNotFoundException("This list has no books.");
        } else  {
            bookList = new HashSet<>(user.getToReadList()) ;

        }
       if (bookList.contains(book)) {
           bookList.remove(book);
       }

       user.setToReadList(bookList);
       userDao.saveUser(user);

        List<BookModel> bookModels = new ModelConverter().toBookModelList(user.getToReadList());

        return RemoveBookFromToReadListResult.builder()
                .withList(bookModels)
                .build();
    }
}
