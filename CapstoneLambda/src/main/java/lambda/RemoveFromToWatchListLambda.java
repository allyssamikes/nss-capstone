package lambda;

import activity.request.RemoveFromToWatchListRequest;
import activity.result.RemoveFromToWatchListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class RemoveFromToWatchListLambda
        extends LambdaActivityRunner<RemoveFromToWatchListRequest, RemoveFromToWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveFromToWatchListRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveFromToWatchListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveFromToWatchListRequest unauthenticatedRequest = input.fromBody(RemoveFromToWatchListRequest.class);
                    return input.fromUserClaims(claims ->
                            RemoveFromToWatchListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveFromToWatchListActivity().handleRequest(request)
        );
    }
}
