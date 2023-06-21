package capstoneservice.dependency;

import capstoneservice.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    GetBookActivity provideGetBookActivity();

    GetMovieActivity provideGetMovieActivity();

    GetTVShowActivity provideGetTVShowActivity();

    GetCurrentlyReadingActivity provideGetCurrentlyReadingActivity();


    GetToReadListActivity provideGetToReadListActivity();


    GetReadListActivity provideGetReadListActivity();


    UpdateUserActivity provideUpdateUserActivity();

    AddToCurrentlyReadingListActivity provideAddToCurrentlyReadingListActivity();



    AddBookToToReadListActivity provideAddBookToToReadListActivity();


    AddBookToReadListActivity provideAddBookToReadListActivity();


    RemoveBookFromCurrentlyReadingActivity provideRemoveBookFromCurrentlyReadingActivity();

    RemoveBookFromToReadListActivity provideRemoveBookFromToReadListActivity();


    GetBookByAuthorActivity provideGetBookByAuthorActivity();


    CreateUserActivity provideCreateUserActivity();

    DeleteUserActivity provideDeleteUserActivity();
}
