package lambda;

import activity.request.RemoveFromCurrentlyWatchingRequest;
import activity.result.RemoveFromCurrentlyWatchingResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class RemoveFromCurrentlyWatchingListLambda
        extends LambdaActivityRunner<RemoveFromCurrentlyWatchingRequest, RemoveFromCurrentlyWatchingResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveFromCurrentlyWatchingRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveFromCurrentlyWatchingRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveFromCurrentlyWatchingRequest unauthenticatedRequest = input.fromBody(RemoveFromCurrentlyWatchingRequest.class);
                    return input.fromUserClaims(claims ->
                            RemoveFromCurrentlyWatchingRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveFromCurrentlyWatchingActivity().handleRequest(request)
        );
    }
}
