package capstoneservice.models;

import capstoneservice.dynamodb.models.Book;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserModel {
    private String userId;
    private String name;
    private List<Book> toReadList;
    private List<Book> currentlyReading;
    private List<Book> readList;
    private List<Object> toWatchList;
    private List<Object> currentlyWatching;
    private List<Object> watchedList;


    public UserModel(String userId, String name, List<Book> toReadList, List<Book> currentlyReading, List<Book> readList, List<Object> toWatchList, List<Object> currentlyWatching, List<Object> watchedList) {
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

    public List<Book> getToReadList() {
        return toReadList;
    }

    public List<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public List<Book> getReadList() {
        return readList;
    }

    public List<Object> getToWatchList() {
        return toWatchList;
    }

    public List<Object> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public List<Object> getWatchedList() {
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
        private List<Book> toReadList;
        private List<Book> currentlyReading;
        private List<Book> readList;
        private List<Object> toWatchList;
        private List<Object> currentlyWatching;
        private List<Object> watchedList;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withToReadList(List<Book> toReadList) {
            this.toReadList = toReadList;
            return this;
        }

        public Builder withCurrentlyReadingList(List<Book> currentlyReading) {
            this.currentlyReading = currentlyReading;
            return this;
        }

        public Builder withReadList(List<Book> readList) {
            this.readList = readList;
            return this;
        }

        public Builder withToWatchList(List<Object> toWatchList) {
            this.toWatchList = toWatchList;
            return this;
        }

        public Builder withCurrentlyWatchingList(List<Object> currentlyWatching) {
            this.currentlyWatching = currentlyWatching;
            return this;
        }

        public Builder withWatchedList(List<Object> watchedList) {
            this.watchedList = watchedList;
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, name, toReadList, currentlyReading, readList, toWatchList, currentlyWatching, watchedList);
        }
    }
}
