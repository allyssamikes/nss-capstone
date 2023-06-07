package capstoneservice.models;

import capstoneservice.dynamodb.models.Book;

import java.util.Objects;
import java.util.Set;

public class UserModel {
    private String userId;
    private String name;
    private Set<Book> toReadList;
    private Set<Book> currentlyReading;
    private Set<Book> readList;
    private Set<Object> toWatchList;
    private Set<Object> currentlyWatching;
    private Set<Object> watchedList;


    public UserModel(String userId, String name, Set<Book> toReadList, Set<Book> currentlyReading, Set<Book> readList, Set<Object> toWatchList, Set<Object> currentlyWatching, Set<Object> watchedList) {
        this.userId = userId;
        this.name = name;
        this.toReadList = toReadList;
        this.currentlyReading = currentlyReading;
        this.readList = readList;
        this.toWatchList = toWatchList;
        this.currentlyWatching = currentlyWatching;
        this.watchedList = watchedList;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getToReadList() {
        return toReadList;
    }

    public Set<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public Set<Book> getReadList() {
        return readList;
    }

    public Set<Object> getToWatchList() {
        return toWatchList;
    }

    public Set<Object> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public Set<Object> getWatchedList() {
        return watchedList;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(userId, userModel.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public static class Builder {
        private String userId;
        private String name;
        private Set<Book> toReadList;
        private Set<Book> currentlyReading;
        private Set<Book> readList;
        private Set<Object> toWatchList;
        private Set<Object> currentlyWatching;
        private Set<Object> watchedList;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withToReadList(Set<Book> toReadList) {
            this.toReadList = toReadList;
            return this;
        }

        public Builder withCurrentlyReadingList(Set<Book> currentlyReading) {
            this.currentlyReading = currentlyReading;
            return this;
        }

        public Builder withReadList(Set<Book> readList) {
            this.readList = readList;
            return this;
        }

        public Builder withToWatchList(Set<Object> toWatchList) {
            this.toWatchList = toWatchList;
            return this;
        }

        public Builder withCurrentlyWatchingList(Set<Object> currentlyWatching) {
            this.currentlyWatching = currentlyWatching;
            return this;
        }

        public Builder withWatchedList(Set<Object> watchedList) {
            this.watchedList = watchedList;
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, name, toReadList, currentlyReading, readList, toWatchList, currentlyWatching, watchedList);
        }
    }
}
