package lambda;

import activity.request.AddBookToReadListRequest;
import activity.result.AddBookToReadListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddBookToReadListLambda
        extends LambdaActivityRunner<AddBookToReadListRequest, AddBookToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookToReadListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookToReadListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddBookToReadListRequest unauthenticatedRequest = input.fromBody(AddBookToReadListRequest.class);
                    return input.fromUserClaims(claims ->
                            AddBookToReadListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookToReadListActivity().handleRequest(request)
        );
    }
}
