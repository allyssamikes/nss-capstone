package capstoneservice.lambda;

import capstoneservice.activity.request.AddBookReviewRequest;
import capstoneservice.activity.result.AddBookReviewResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddBookReviewLambda
        extends LambdaActivityRunner<AddBookReviewRequest, AddBookReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookReviewRequest> input, Context context) {
        AddBookReviewRequest unauthenticatedRequest = input.fromBody(AddBookReviewRequest.class);
        return super.runActivity(
                () -> {
                    return
                            AddBookReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookReviewActivity().handleRequest(request)
        );
    }
}

