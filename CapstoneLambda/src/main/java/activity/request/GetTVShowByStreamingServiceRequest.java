package activity.request;

import dynamodb.models.STREAMING_SERVICE;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetTVShowByStreamingServiceRequest {
    private final STREAMING_SERVICE service;
    public GetTVShowByStreamingServiceRequest(STREAMING_SERVICE service) {
        this.service = service;
    }

    public STREAMING_SERVICE getService() {
        return service;
    }

    @Override
    public String toString() {
        return "GetTVShowByStreamingServiceRequest{" +
                "service=" + service +
                '}';
    }
        //CHECKSTYLE:OFF:Builder

        public static Builder builder() {
            return new Builder();
        }

    @JsonPOJOBuilder
    public static class Builder {
    private STREAMING_SERVICE service;


    public Builder withService(STREAMING_SERVICE service) {
        this.service = service;
        return this;
    }

    public GetTVShowByStreamingServiceRequest build() {
        return new GetTVShowByStreamingServiceRequest(service);
    }
}
}
