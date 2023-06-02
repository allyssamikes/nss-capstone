package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Set;

@DynamoDBTable(tableName ="users")
public class User {

    private String userId;
    private String name;
    private Set<Book> toReadList;
    private Set<Book> currentlyReading;
    private Set<Book> readList;
    private Set<Object> toWatchList;
    private Set<Object> currentlyWatching;
    private Set<Object> watchedList;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "toReadList")
    public Set<Book> getToReadList() {
        return toReadList;
    }

    public void setToReadList(Set<Book> toReadList) {
        this.toReadList = toReadList;
    }

    @DynamoDBAttribute(attributeName = "currentlyReadingList")
    public Set<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Set<Book> currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    @DynamoDBAttribute(attributeName = "readList")
    public Set<Book> getReadList() {
        return readList;
    }

    public void setReadList(Set<Book> readList) {
        this.readList = readList;
    }

    @DynamoDBAttribute(attributeName = "toWatchList")
    public Set<Object> getToWatchList() {
        return toWatchList;
    }

    public void setToWatchList(Set<Object> toWatchList) {
        this.toWatchList = toWatchList;
    }

    @DynamoDBAttribute(attributeName = "toCurrentlyWatchingList")
    public Set<Object> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public void setCurrentlyWatching(Set<Object> currentlyWatching) {
        this.currentlyWatching = currentlyWatching;
    }

    @DynamoDBAttribute(attributeName = "toWatchedList")
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
