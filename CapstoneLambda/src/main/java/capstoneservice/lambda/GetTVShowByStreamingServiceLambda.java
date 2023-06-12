package capstoneservice.lambda;

import capstoneservice.activity.request.GetMovieByStreamingServiceRequest;
import capstoneservice.activity.request.GetTVShowByStreamingServiceRequest;
import capstoneservice.activity.result.GetTVShowByStreamingServiceResult;
import capstoneservice.dynamodb.models.STREAMING_SERVICE;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetTVShowByStreamingServiceLambda
        extends LambdaActivityRunner<GetTVShowByStreamingServiceRequest, GetTVShowByStreamingServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTVShowByStreamingServiceRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTVShowByStreamingServiceRequest> input, Context context) {
        return  super.runActivity(
                () -> input.fromQuery(query ->
                        GetTVShowByStreamingServiceRequest.builder()
                                .withService(STREAMING_SERVICE.valueOf(query.get("service")))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowByStreamingServiceActivity().handleRequest(request)
        );
    }
}


