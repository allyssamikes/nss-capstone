package capstoneservice.lambda;

import capstoneservice.activity.request.GetTVShowRequest;
import capstoneservice.activity.result.GetTVShowResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetTVShowLambda extends LambdaActivityRunner<GetTVShowRequest, GetTVShowResult>
        implements RequestHandler<LambdaRequest<GetTVShowRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTVShowRequest> input, Context context) {
        return super.runActivity(
                () ->
                        input.fromPath(path ->
                        GetTVShowRequest.builder()
                                .withTitle(path.get("title"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowActivity().handleRequest(request)
        );
    }
}
