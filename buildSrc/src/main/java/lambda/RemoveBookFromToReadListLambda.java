package lambda;

import activity.request.RemoveBookFromToReadListRequest;
import activity.result.RemoveBookFromToReadListResult;
import org.gradle.process.internal.worker.RequestHandler;

public class RemoveBookFromToReadListLambda
        extends LambdaActivityRunner<RemoveBookFromToReadListRequest, RemoveBookFromToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveBookFromToReadListRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveBookFromToReadListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveBookFromToReadListRequest unauthenticatedRequest = input.fromBody(RemoveBookFromToReadListRequest.class);
                    return input.fromUserClaims(claims ->
                            RemoveBookFromToReadListRequest.builder()
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveBookFromToReadListActivity().handleRequest(request)
        );
    }
}
