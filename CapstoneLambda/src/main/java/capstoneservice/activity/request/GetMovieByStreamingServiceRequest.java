package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = GetMovieByStreamingServiceRequest.Builder.class)
public class GetMovieByStreamingServiceRequest {

    private final String service;
    public GetMovieByStreamingServiceRequest(String service) {
        this.service = service;
    }

    public String getService() {
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
        private String service;

        public Builder withService(String service) {
            this.service = service;
            return this;
        }

        public GetMovieByStreamingServiceRequest build() {
            return new GetMovieByStreamingServiceRequest(service);
        }
    }
}
