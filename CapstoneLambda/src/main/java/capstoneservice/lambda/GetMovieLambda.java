package capstoneservice.lambda;

import capstoneservice.activity.request.GetMovieRequest;
import capstoneservice.activity.result.GetMovieResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;



public class GetMovieLambda extends LambdaActivityRunner<GetMovieRequest, GetMovieResult>
            implements RequestHandler<LambdaRequest<GetMovieRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetMovieRequest> input, Context context) {
            return super.runActivity(
                    () ->       input.fromPath(path ->
                                        GetMovieRequest.builder()
                                                .withTitle(path.get("title"))
                                                .withDirector(path.get("director"))
                                                .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetMovieActivity().handleRequest(request)
            );
        }
    }
