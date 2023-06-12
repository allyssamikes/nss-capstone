package capstoneservice.lambda;

import capstoneservice.activity.request.GetMovieByStreamingServiceRequest;
import capstoneservice.activity.result.GetMovieByStreamingServiceResult;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetMovieByStreamingServiceLambda         extends LambdaActivityRunner<GetMovieByStreamingServiceRequest, GetMovieByStreamingServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMovieByStreamingServiceRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMovieByStreamingServiceRequest> input, Context context) {
     return  super.runActivity(
             () -> input.fromQuery(query ->
                            GetMovieByStreamingServiceRequest.builder()
                                    .withService(STREAMING_SERVICE.valueOf(query.get("service")))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetMovieByStreamingServiceActivity().handleRequest(request)
        );
    }
}
