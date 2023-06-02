package lambda;

import activity.request.GetBookRequest;
import activity.result.GetBookResult;
import org.apache.log4j.LogManager;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetBookLambda extends LambdaActivityRunner<GetBookRequest, GetBookResult>
        implements RequestHandler<LambdaRequest<GetBookRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetBookRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetBookRequest.builder()
                                .withIsbn(path.get("isbn"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetBookActivity().handleRequest(request)
        );
    }
}

