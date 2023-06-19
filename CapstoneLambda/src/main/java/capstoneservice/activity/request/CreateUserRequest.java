package capstoneservice.activity.request;

import capstoneservice.dynamodb.models.Book;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;


@JsonDeserialize(builder = CreateUserRequest.Builder.class)
public class CreateUserRequest {

    private final String userId;
    private final String name;
    private final List<Book> toReadList;
    private final List<Book> currentlyReading;
    private final List<Book> readList;
    private final List<Object> toWatchList;
    private final List<Object> currentlyWatching;
    private final List<Object> watchedList;

    public CreateUserRequest(String userId, String name, List<Book> toReadList, List<Book> currentlyReading, List<Book> readList, List<Object> toWatchList, List<Object> currentlyWatching, List<Object> watchedList) {
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
        return new ArrayList<>();
    }

    public List<Book> getCurrentlyReading() {
        return new ArrayList<>();
    }

    public List<Book> getReadList() {
        return new ArrayList<>();
    }

    public List<Object> getToWatchList() {
        return new ArrayList<>();
    }

    public List<Object> getCurrentlyWatching() {
        return new ArrayList<>();
    }

    public List<Object> getWatchedList() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
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

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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

        public Builder withCurrentlyReading(List<Book> currentlyReading) {
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

        public Builder withCurrentlyWatching(List<Object> currentlyWatching) {
            this.currentlyWatching = currentlyWatching;
            return this;
        }

        public Builder withWatchedList(List<Object> watchedList) {
            this.watchedList = watchedList;
            return this;
        }
        public CreateUserRequest build() {
            return new CreateUserRequest(userId, name, toReadList, currentlyReading, readList, toWatchList, currentlyWatching, watchedList );
        }
    }
}
