package capstoneservice.lambda;

import capstoneservice.activity.request.AddBookToToReadListRequest;
import capstoneservice.activity.result.AddBookToToReadListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddToToReadListLambda
        extends LambdaActivityRunner<AddBookToToReadListRequest, AddBookToToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookToToReadListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookToToReadListRequest> input, Context context) {
        AddBookToToReadListRequest unauthenticatedRequest = input.fromBody(AddBookToToReadListRequest.class);
        return super.runActivity(
                () -> {
                    return
                            AddBookToToReadListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookToToReadListActivity().handleRequest(request)
        );
    }
}
