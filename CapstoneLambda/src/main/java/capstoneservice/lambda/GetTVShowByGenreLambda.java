package capstoneservice.lambda;

import capstoneservice.activity.request.GetTVShowByGenreRequest;
import capstoneservice.activity.result.GetTVShowByGenreResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetTVShowByGenreLambda
        extends LambdaActivityRunner<GetTVShowByGenreRequest, GetTVShowByGenreResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTVShowByGenreRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTVShowByGenreRequest> input, Context context) {
        return  super.runActivity(
                () -> input.fromQuery(query ->
                        GetTVShowByGenreRequest.builder()
                                .withGenre(query.get("genre"))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowByGenreActivity().handleRequest(request)
        );
    }
}


