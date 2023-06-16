package capstoneservice.lambda;

import capstoneservice.activity.request.GetTVShowByGenreRequest;
import capstoneservice.activity.result.GetTVShowByGenreResult;
import capstoneservice.dynamodb.models.GENRE;
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
                                .withGenre(GENRE.valueOf(query.get("genre")))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTVShowByGenreActivity().handleRequest(request)
        );
    }
}


