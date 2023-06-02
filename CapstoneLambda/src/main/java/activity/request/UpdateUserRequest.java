package activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class UpdateUserRequest {
    private final String userId;
    private final String name;

    public UpdateUserRequest(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }


    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
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

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateUserRequest build(){
            return new UpdateUserRequest(userId,name);
        }
    }
}
