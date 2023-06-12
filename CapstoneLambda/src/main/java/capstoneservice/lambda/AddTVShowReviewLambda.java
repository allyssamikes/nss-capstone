package capstoneservice.lambda;

import capstoneservice.activity.request.AddTVShowReviewRequest;
import capstoneservice.activity.result.AddTVShowReviewResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddTVShowReviewLambda
        extends LambdaActivityRunner<AddTVShowReviewRequest, AddTVShowReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddTVShowReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddTVShowReviewRequest> input, Context context) {
        AddTVShowReviewRequest unauthenticatedRequest = input.fromBody(AddTVShowReviewRequest.class);
        return super.runActivity(
                () -> {

                    return
                            AddTVShowReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddTVShowReviewActivity().handleRequest(request)
        );
    }
}
