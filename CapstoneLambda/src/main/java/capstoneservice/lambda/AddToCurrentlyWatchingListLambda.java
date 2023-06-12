package capstoneservice.lambda;

import capstoneservice.activity.request.AddToCurrentlyWatchingListRequest;
import capstoneservice.activity.result.AddToCurrentlyWatchingListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddToCurrentlyWatchingListLambda
        extends LambdaActivityRunner<AddToCurrentlyWatchingListRequest, AddToCurrentlyWatchingListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToCurrentlyWatchingListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToCurrentlyWatchingListRequest> input, Context context) {
        AddToCurrentlyWatchingListRequest unauthenticatedRequest = input.fromBody(AddToCurrentlyWatchingListRequest.class);
     return super.runActivity(
                () -> {
                    return AddToCurrentlyWatchingListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToCurrentlyWatchingListActivity().handleRequest(request)
        );
    }
}
