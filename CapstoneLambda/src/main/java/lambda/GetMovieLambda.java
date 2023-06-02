package lambda;

import activity.request.GetBookRequest;
import activity.request.GetMovieRequest;
import activity.result.GetBookResult;
import activity.result.GetMovieResult;
import org.apache.log4j.LogManager;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;



public class GetMovieLambda extends LambdaActivityRunner<GetMovieRequest, GetMovieResult>
            implements RequestHandler<LambdaRequest<GetMovieRequest>, LambdaResponse> {


        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetMovieRequest> input, Context context) {
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
