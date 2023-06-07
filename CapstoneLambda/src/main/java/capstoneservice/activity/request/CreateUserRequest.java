package capstoneservice.activity.request;

import capstoneservice.dynamodb.models.Book;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.HashSet;
import java.util.Set;


@JsonDeserialize(builder = CreateUserRequest.Builder.class)
public class CreateUserRequest {

    private final String userId;
    private final String name;
    private final Set<Book> toReadList;
    private final Set<Book> currentlyReading;
    private final Set<Book> readList;
    private final Set<Object> toWatchList;
    private final Set<Object> currentlyWatching;
    private final Set<Object> watchedList;

    public CreateUserRequest(String userId, String name, Set<Book> toReadList, Set<Book> currentlyReading, Set<Book> readList, Set<Object> toWatchList, Set<Object> currentlyWatching, Set<Object> watchedList) {
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
        return new HashSet<>();
    }

    public Set<Book> getCurrentlyReading() {
        return new HashSet<>();
    }

    public Set<Book> getReadList() {
        return new HashSet<>();
    }

    public Set<Object> getToWatchList() {
        return new HashSet<>();
    }

    public Set<Object> getCurrentlyWatching() {
        return new HashSet<>();
    }

    public Set<Object> getWatchedList() {
        return new HashSet<>();
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

        public Builder withCurrentlyReading(Set<Book> currentlyReading) {
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

        public Builder withCurrentlyWatching(Set<Object> currentlyWatching) {
            this.currentlyWatching = currentlyWatching;
            return this;
        }

        public Builder withWatchedList(Set<Object> watchedList) {
            this.watchedList = watchedList;
            return this;
        }
        public CreateUserRequest build() {
            return new CreateUserRequest(userId, name, toReadList, currentlyReading, readList, toWatchList, currentlyWatching, watchedList );
        }
    }
}
