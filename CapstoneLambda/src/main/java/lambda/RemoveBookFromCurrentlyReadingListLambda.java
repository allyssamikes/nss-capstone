package lambda;

import activity.request.RemoveBookFromCurrentlyReadingRequest;
import activity.result.RemoveBookFromCurrentlyReadingResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class RemoveBookFromCurrentlyReadingListLambda
        extends LambdaActivityRunner<RemoveBookFromCurrentlyReadingRequest, RemoveBookFromCurrentlyReadingResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveBookFromCurrentlyReadingRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveBookFromCurrentlyReadingRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveBookFromCurrentlyReadingRequest unauthenticatedRequest = input.fromBody(RemoveBookFromCurrentlyReadingRequest.class);
                    return input.fromUserClaims(claims ->
                            RemoveBookFromCurrentlyReadingRequest.builder()
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build());

                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveBookFromCurrentlyReadingActivity().handleRequest(request)
        );
    }
}
