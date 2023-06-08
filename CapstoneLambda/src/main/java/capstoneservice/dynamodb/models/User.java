package capstoneservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Set;

@DynamoDBTable(tableName ="users")
public class User {

    private String userId;
    private String name;
    private List<Book> toReadList;
    private List<Book> currentlyReading;
    private List<Book> readList;
    private List<Object> toWatchList;
    private List<Object> currentlyWatching;
    private List<Object> watchedList;

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
    public List<Book> getToReadList() {
        return toReadList;
    }

    public void setToReadList(List<Book> toReadList) {
        this.toReadList = toReadList;
    }

    @DynamoDBAttribute(attributeName = "currentlyReading")
    public List<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(List<Book> currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    @DynamoDBAttribute(attributeName = "readList")
    public List<Book> getReadList() {
        return readList;
    }

    public void setReadList(List<Book> readList) {
        this.readList = readList;
    }

    @DynamoDBAttribute(attributeName = "toWatchList")
    public List<Object> getToWatchList() {
        return toWatchList;
    }

    public void setToWatchList(List<Object> toWatchList) {
        this.toWatchList = toWatchList;
    }

    @DynamoDBAttribute(attributeName = "currentlyWatching")
    public List<Object> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public void setCurrentlyWatching(List<Object> currentlyWatching) {
        this.currentlyWatching = currentlyWatching;
    }

    @DynamoDBAttribute(attributeName = "watchedList")
    public List<Object> getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(List<Object> watchedList) {
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
