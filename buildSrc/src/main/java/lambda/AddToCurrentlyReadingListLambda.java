package lambda;

import activity.request.AddToCurrentlyReadingListRequest;
import activity.result.AddToCurrentlyReadingListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddToCurrentlyReadingListLambda
        extends LambdaActivityRunner<AddToCurrentlyReadingListRequest, AddToCurrentlyReadingListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToCurrentlyReadingListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToCurrentlyReadingListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddToCurrentlyReadingListRequest unauthenticatedRequest = input.fromBody(AddToCurrentlyReadingListRequest.class);
                    return input.fromUserClaims(claims ->
                            AddToCurrentlyReadingListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToCurrentlyReadingListActivity().handleRequest(request)
        );
    }
}
