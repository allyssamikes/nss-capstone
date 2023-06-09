package capstoneservice.lambda;

import capstoneservice.activity.request.RemoveBookFromCurrentlyReadingRequest;
import capstoneservice.activity.result.RemoveBookFromCurrentlyReadingResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class RemoveBookFromCurrentlyReadingListLambda
        extends LambdaActivityRunner<RemoveBookFromCurrentlyReadingRequest, RemoveBookFromCurrentlyReadingResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveBookFromCurrentlyReadingRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveBookFromCurrentlyReadingRequest> input, Context context) {
        RemoveBookFromCurrentlyReadingRequest unauthenticatedRequest = input.fromBody(RemoveBookFromCurrentlyReadingRequest.class);
        return super.runActivity(
                () -> {
                    return
                            RemoveBookFromCurrentlyReadingRequest.builder()
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build();

                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveBookFromCurrentlyReadingActivity().handleRequest(request)
        );
    }
}
