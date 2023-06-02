package lambda;

import activity.request.GetTVShowByStreamingServiceRequest;
import activity.result.GetTVShowByStreamingServiceResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetTVShowByStreamingServiceLambda
        extends LambdaActivityRunner<GetTVShowByStreamingServiceRequest, GetTVShowByStreamingServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTVShowByStreamingServiceRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTVShowByStreamingServiceRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetTVShowByStreamingServiceRequest unauthenticatedRequest = input.fromBody(GetTVShowByStreamingServiceRequest.class);
                    return input.fromUserClaims(claims ->
                            GetTVShowByStreamingServiceRequest.builder()
                                    .withService(unauthenticatedRequest.getService())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowByStreamingServiceActivity().handleRequest(request)
        );
    }
}


