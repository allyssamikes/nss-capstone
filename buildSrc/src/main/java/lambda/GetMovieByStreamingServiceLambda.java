package lambda;

import activity.request.GetMovieByStreamingServiceRequest;
import activity.result.GetMovieByStreamingServiceResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class GetMovieByStreamingServiceLambda         extends LambdaActivityRunner<GetMovieByStreamingServiceRequest, GetMovieByStreamingServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMovieByStreamingServiceRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMovieByStreamingServiceRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetMovieByStreamingServiceRequest unauthenticatedRequest = input.fromBody(GetMovieByStreamingServiceRequest.class);
                    return input.fromUserClaims(claims ->
                            GetMovieByStreamingServiceRequest.builder()
                                    .withService(unauthenticatedRequest.getService())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetMovieByStreamingServiceActivity().handleRequest(request)
        );
    }
}
