package capstoneservice.lambda;

import capstoneservice.activity.request.AddBookToReadListRequest;
import capstoneservice.activity.result.AddBookToReadListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddToReadListLambda
        extends LambdaActivityRunner<AddBookToReadListRequest, AddBookToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookToReadListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookToReadListRequest> input, Context context) {
        AddBookToReadListRequest unauthenticatedRequest = input.fromBody(AddBookToReadListRequest.class);
        return super.runActivity(
                () -> {
                    return
                            AddBookToReadListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookToReadListActivity().handleRequest(request)
        );
    }
}
