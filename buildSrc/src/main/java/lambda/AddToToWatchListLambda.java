package lambda;

import activity.request.AddToToWatchListRequest;
import activity.result.AddToToWatchListResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class AddToToWatchListLambda
        extends LambdaActivityRunner<AddToToWatchListRequest, AddToToWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToToWatchListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToToWatchListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddToToWatchListRequest unauthenticatedRequest = input.fromBody(AddToToWatchListRequest.class);
                    return input.fromUserClaims(claims ->
                            AddToToWatchListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToToWatchListActivity().handleRequest(request)
        );
    }
}
