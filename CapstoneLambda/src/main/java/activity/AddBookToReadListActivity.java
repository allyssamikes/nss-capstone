package activity;

import activity.request.AddBookToReadListRequest;
import activity.result.AddBookToReadListResult;
import converters.ModelConverter;
import dynamodb.BookDao;
import dynamodb.UserDao;
import dynamodb.models.Book;
import dynamodb.models.User;
import exceptions.BookNotFoundException;
import exceptions.UserNotFoundException;
import models.BookModel;

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
