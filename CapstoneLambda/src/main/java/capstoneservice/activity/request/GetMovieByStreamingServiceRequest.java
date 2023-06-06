package capstoneservice.activity.request;

import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetMovieByStreamingServiceRequest {

    private final STREAMING_SERVICE service;
    public GetMovieByStreamingServiceRequest(STREAMING_SERVICE service) {
        this.service = service;
    }

    public STREAMING_SERVICE getService() {
        return service;
    }

    @Override
    public String toString() {
        return "GetMovieByStreamingServiceRequest{" +
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

        public GetMovieByStreamingServiceRequest build() {
            return new GetMovieByStreamingServiceRequest(service);
        }
    }
}
