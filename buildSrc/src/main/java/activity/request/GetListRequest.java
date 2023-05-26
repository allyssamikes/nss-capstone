package activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetListRequest {
    private final String userId;

    public GetListRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetListRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetListRequest build() {
            return new GetListRequest(userId);
        }
    }
}
