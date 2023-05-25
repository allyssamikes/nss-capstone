package lambda;

import activity.request.GetTVShowRequest;
import activity.result.GetTVShowResult;
import org.apache.log4j.LogManager;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;
import java.util.logging.Logger;

public class GetTVShowLambda extends LambdaActivityRunner<GetTVShowRequest, GetTVShowResult>
        implements RequestHandler<LambdaRequest<GetTVShowRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTVShowRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetTVShowRequest.builder()
                                .withTitle(path.get("title"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowActivity().handleRequest(request)
        );
    }
}
