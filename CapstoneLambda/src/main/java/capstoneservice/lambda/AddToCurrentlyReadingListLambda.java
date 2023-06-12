package capstoneservice.lambda;

import capstoneservice.activity.request.AddToCurrentlyReadingListRequest;
import capstoneservice.activity.result.AddToCurrentlyReadingListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddToCurrentlyReadingListLambda
        extends LambdaActivityRunner<AddToCurrentlyReadingListRequest, AddToCurrentlyReadingListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToCurrentlyReadingListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToCurrentlyReadingListRequest> input, Context context) {
        AddToCurrentlyReadingListRequest unauthenticatedRequest = input.fromBody(AddToCurrentlyReadingListRequest.class);
        return super.runActivity(
                () -> {
                    return
                            AddToCurrentlyReadingListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToCurrentlyReadingListActivity().handleRequest(request)
        );
    }
}
