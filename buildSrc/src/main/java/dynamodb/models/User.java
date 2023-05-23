package dynamodb.models;

import java.util.Set;

public class User {

    private String userId;
    private String name;
    private Set<Book> toReadList;
    private Set<Book> currentlyReading;
    private Set<Book> readList;
    private Set<Object> toWatchList;
    private Set<Object> currentlyWatching;
    private Set<Object> watchedList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getToReadList() {
        return toReadList;
    }

    public void setToReadList(Set<Book> toReadList) {
        this.toReadList = toReadList;
    }

    public Set<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Set<Book> currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public Set<Book> getReadList() {
        return readList;
    }

    public void setReadList(Set<Book> readList) {
        this.readList = readList;
    }

    public Set<Object> getToWatchList() {
        return toWatchList;
    }

    public void setToWatchList(Set<Object> toWatchList) {
        this.toWatchList = toWatchList;
    }

    public Set<Object> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public void setCurrentlyWatching(Set<Object> currentlyWatching) {
        this.currentlyWatching = currentlyWatching;
    }

    public Set<Object> getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(Set<Object> watchedList) {
        this.watchedList = watchedList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", toReadList=" + toReadList +
                ", currentlyReading=" + currentlyReading +
                ", readList=" + readList +
                ", toWatchList=" + toWatchList +
                ", currentlyWatching=" + currentlyWatching +
                ", watchedList=" + watchedList +
                '}';
    }
}
