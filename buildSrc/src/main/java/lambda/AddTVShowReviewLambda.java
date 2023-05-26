package lambda;

import activity.request.AddTVShowReviewRequest;
import activity.result.AddTVShowReviewResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class AddTVShowReviewLambda
        extends LambdaActivityRunner<AddTVShowReviewRequest, AddTVShowReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddTVShowReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddTVShowReviewRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddTVShowReviewRequest unauthenticatedRequest = input.fromBody(AddTVShowReviewRequest.class);
                    return input.fromUserClaims(claims ->
                            AddTVShowReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddTVShowReviewActivity().handleRequest(request)
        );
    }
}
