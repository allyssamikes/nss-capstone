package capstoneservice.lambda;

import capstoneservice.activity.request.CreateReviewRequest;
import capstoneservice.activity.result.CreateReviewResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class CreateReviewLambda
        extends LambdaActivityRunner<CreateReviewRequest, CreateReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateReviewRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateReviewRequest unauthenticatedRequest = input.fromBody(CreateReviewRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withReview(unauthenticatedRequest.getReview())
                                    .withRating(unauthenticatedRequest.getRating())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateReviewActivity().handleRequest(request)
        );
    }
}
