package capstoneservice.lambda;

import capstoneservice.activity.GetMovieActivity;
import capstoneservice.activity.request.GetMovieRequest;
import capstoneservice.activity.result.GetMovieResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;



public class GetMovieLambda extends LambdaActivityRunner<GetMovieRequest, GetMovieResult>
            implements RequestHandler<LambdaRequest<GetMovieRequest>, LambdaResponse> {


        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetMovieRequest> input, Context context) {
            return super.runActivity(
                    () -> {
                        GetMovieRequest request = input.fromBody(GetMovieRequest.class);
                        return
                                input.fromUserClaims(claims ->
                                        GetMovieRequest.builder()
                                                .withTitle(request.getTitle())
                                                .withDirector(request.getDirector())
                                                .build())
                    },
                    (request, serviceComponent) ->
                            serviceComponent.provideGetMovieActivity().handleRequest(request)
            );
        }
    }
