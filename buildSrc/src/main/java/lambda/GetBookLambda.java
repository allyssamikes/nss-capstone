package lambda;

import activity.request.GetBookRequest;
import activity.result.GetBookResult;
import org.apache.log4j.LogManager;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;
import java.util.logging.Logger;

public class GetBookLambda extends LambdaActivityRunner<GetBookRequest, GetBookResult>
        implements RequestHandler<LambdaRequest<GetBookRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetBookRequest> input, Context context) {
        log.info("handleRequest");
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

