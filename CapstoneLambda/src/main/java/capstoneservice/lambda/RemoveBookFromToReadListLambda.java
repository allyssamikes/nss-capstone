package capstoneservice.lambda;

import capstoneservice.activity.request.RemoveBookFromToReadListRequest;
import capstoneservice.activity.result.RemoveBookFromToReadListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class RemoveBookFromToReadListLambda
        extends LambdaActivityRunner<RemoveBookFromToReadListRequest, RemoveBookFromToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveBookFromToReadListRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveBookFromToReadListRequest> input, Context context) {
        RemoveBookFromToReadListRequest unauthenticatedRequest = input.fromBody(RemoveBookFromToReadListRequest.class);
        return super.runActivity(
                () -> {
                    return
                            RemoveBookFromToReadListRequest.builder()
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveBookFromToReadListActivity().handleRequest(request)
        );
    }
}
