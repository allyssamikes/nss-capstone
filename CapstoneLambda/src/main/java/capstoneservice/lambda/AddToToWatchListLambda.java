package capstoneservice.lambda;

import capstoneservice.activity.request.AddToToWatchListRequest;
import capstoneservice.activity.result.AddToToWatchListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddToToWatchListLambda
        extends LambdaActivityRunner<AddToToWatchListRequest, AddToToWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToToWatchListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToToWatchListRequest> input, Context context) {
        AddToToWatchListRequest unauthenticatedRequest = input.fromBody(AddToToWatchListRequest.class);
        return super.runActivity(
                () -> {
                  return  AddToToWatchListRequest.builder()
                            .withUserId(unauthenticatedRequest.getUserId())
                            .withTitle(unauthenticatedRequest.getTitle())
                            .withDirector(unauthenticatedRequest.getDirector())
                            .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToToWatchListActivity().handleRequest(request)
        );
    }
}
