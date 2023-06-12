package capstoneservice.lambda;

import capstoneservice.activity.request.AddMovieReviewRequest;
import capstoneservice.activity.result.AddMovieReviewResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddMovieReviewLambda
        extends LambdaActivityRunner<AddMovieReviewRequest, AddMovieReviewResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddMovieReviewRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddMovieReviewRequest> input, Context context) {
        AddMovieReviewRequest unauthenticatedRequest = input.fromBody(AddMovieReviewRequest.class);
        return super.runActivity(
                () -> {
                    return
                            AddMovieReviewRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withUUID(unauthenticatedRequest.getUUIDOfEntity())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddMovieReviewActivity().handleRequest(request)
        );
    }
}
