package capstoneservice.lambda;

import capstoneservice.activity.request.RemoveFromToWatchListRequest;
import capstoneservice.activity.result.RemoveFromToWatchListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class RemoveFromToWatchListLambda
        extends LambdaActivityRunner<RemoveFromToWatchListRequest, RemoveFromToWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveFromToWatchListRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveFromToWatchListRequest> input, Context context) {
        RemoveFromToWatchListRequest unauthenticatedRequest = input.fromBody(RemoveFromToWatchListRequest.class);
        return super.runActivity(
                () -> {
                    return
                            RemoveFromToWatchListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveFromToWatchListActivity().handleRequest(request)
        );
    }
}
