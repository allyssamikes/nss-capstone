package lambda;

import activity.request.GetBookRequest;
import activity.result.GetBookResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetBookLambda extends LambdaActivityRunner<GetBookRequest, GetBookResult>
        implements RequestHandler<LambdaRequest<GetBookRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetBookRequest> input, Context context) {
        log.info("GetBookLambda handleRequest");
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

