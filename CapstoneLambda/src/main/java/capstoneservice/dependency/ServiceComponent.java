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

    GetCurrentlyWatchingActivity provideGetCurrentlyWatchingActivity();

    GetToReadListActivity provideGetToReadListActivity();

    GetToWatchListActivity provideGetToWatchActivity();

    GetReadListActivity provideGetReadListActivity();

    GetWatchedListActivity provideGetWatchedActivity();

    CreateReviewActivity provideCreateReviewActivity();

    UpdateUserActivity provideUpdateUserActivity();

    AddToCurrentlyReadingListActivity provideAddToCurrentlyReadingListActivity();

    AddToCurrentlyWatchingListActivity provideAddToCurrentlyWatchingListActivity();

    AddBookToToReadListActivity provideAddBookToToReadListActivity();

    AddToToWatchListActivity provideAddToToWatchListActivity();

    AddBookToReadListActivity provideAddBookToReadListActivity();

    AddToWatchedListActivity provideAddToWatchedListActivity();

    RemoveBookFromCurrentlyReadingActivity provideRemoveBookFromCurrentlyReadingActivity();

    RemoveBookFromToReadListActivity provideRemoveBookFromToReadListActivity();

    RemoveFromToWatchListActivity provideRemoveFromToWatchListActivity();

    RemoveFromCurrentlyWatchingActivity provideRemoveFromCurrentlyWatchingActivity();

    AddBookReviewActivity provideAddBookReviewActivity();

    AddTVShowReviewActivity provideAddTVShowReviewActivity();

    AddMovieReviewActivity provideAddMovieReviewActivity();

    GetBookByAuthorActivity provideGetBookByGenreActivity();

    GetTVShowByStreamingServiceActivity provideGetTVShowByStreamingServiceActivity();

    GetMovieByStreamingServiceActivity provideGetMovieByStreamingServiceActivity();

    CreateUserActivity provideCreateUserActivity();

    DeleteUserActivity provideDeleteUserActivity();
}
