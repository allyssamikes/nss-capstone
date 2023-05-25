package lambda;

import activity.request.GetBookRequest;
import activity.request.GetMovieRequest;
import activity.result.GetBookResult;
import activity.result.GetMovieResult;
import org.apache.log4j.LogManager;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;
import java.util.logging.Logger;


public class GetMovieLambda extends LambdaActivityRunner<GetMovieRequest, GetMovieResult>
            implements RequestHandler<LambdaRequest<GetMovieRequest>, LambdaResponse> {

        private final Logger log = LogManager.getLogger();

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetMovieRequest> input, Context context) {
            log.info("handleRequest");
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetMovieRequest.builder()
                                    .withTitle(path.get("title"))
                                    .withDirector(path.get("director"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetMovieActivity().handleRequest(request)
            );
        }
    }
