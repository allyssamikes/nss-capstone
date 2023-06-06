package capstoneservice.lambda;

import capstoneservice.activity.request.GetListRequest;
import capstoneservice.activity.result.GetWatchedListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetWatchedListLambda
        extends LambdaActivityRunner<GetListRequest, GetWatchedListResult>
        implements RequestHandler<LambdaRequest<GetListRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetListRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetListRequest.builder()
                                .withUserId(path.get("userId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetWatchedActivity().handleRequest(request)
        );
    }
}
