package lambda;

import activity.request.AddToCurrentlyWatchingListRequest;
import activity.result.AddToCurrentlyWatchingListResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class AddToCurrentlyWatchingListLambda
        extends LambdaActivityRunner<AddToCurrentlyWatchingListRequest, AddToCurrentlyWatchingListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToCurrentlyWatchingListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToCurrentlyWatchingListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddToCurrentlyWatchingListRequest unauthenticatedRequest = input.fromBody(AddToCurrentlyWatchingListRequest.class);
                    return input.fromUserClaims(claims ->
                            AddToCurrentlyWatchingListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToCurrentlyWatchingListActivity().handleRequest(request)
        );
    }
}
