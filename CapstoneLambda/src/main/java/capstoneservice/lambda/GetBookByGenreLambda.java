package capstoneservice.lambda;

import capstoneservice.activity.request.GetBookByGenreRequest;
import capstoneservice.activity.result.GetBookByGenreResult;
import capstoneservice.dynamodb.models.GENRE;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetBookByGenreLambda  extends LambdaActivityRunner<GetBookByGenreRequest, GetBookByGenreResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetBookByGenreRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetBookByGenreRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                            GetBookByGenreRequest.builder()
                                    .withGenre(GENRE.valueOf(query.get("genre")))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetBookByGenreActivity().handleRequest(request)
        );
    }
}
