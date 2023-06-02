package lambda;

import activity.request.AddBookReviewRequest;
import activity.result.AddBookReviewResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddBookReviewLambda
        extends LambdaActivityRunner<AddBookReviewRequest, AddBookReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookReviewRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddBookReviewRequest unauthenticatedRequest = input.fromBody(AddBookReviewRequest.class);
                    return input.fromUserClaims(claims ->
                            AddBookReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookReviewActivity().handleRequest(request)
        );
    }
}

