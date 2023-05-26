package activity.request;

import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class AddTVShowReviewRequest {

    private final String title;

    private final String userId;

    private final String UUIDOfEntity;

    public AddTVShowReviewRequest(String title,String userId, String UUIDOfEntity) {
        this.title = title;
        this.userId = userId;
        this.UUIDOfEntity = UUIDOfEntity;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }


    @Override
    public String toString() {
        return "AddTVShowReviewRequest{" +
                "title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                ", UUIDOfEntity='" + UUIDOfEntity + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    @JsonPOJOBuilder
    public static class Builder {

        private String title;

        private String userId;

        private String UUIDOfEntity;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUUID(String UUIDOfEntity) {
            this.UUIDOfEntity = UUIDOfEntity;
            return this;
        }

        public AddTVShowReviewRequest build() {
            return new AddTVShowReviewRequest(title, userId, UUIDOfEntity);
        }
    }
}

