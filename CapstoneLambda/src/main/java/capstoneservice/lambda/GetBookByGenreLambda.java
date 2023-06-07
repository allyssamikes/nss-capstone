package capstoneservice.lambda;

import capstoneservice.activity.request.GetBookByGenreRequest;
import capstoneservice.activity.result.GetBookByGenreResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetBookByGenreLambda  extends LambdaActivityRunner<GetBookByGenreRequest, GetBookByGenreResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetBookByGenreRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetBookByGenreRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetBookByGenreRequest unauthenticatedRequest = input.fromBody(GetBookByGenreRequest.class);
                    return input.fromUserClaims(claims ->
                            GetBookByGenreRequest.builder()
                                    .withGenre(unauthenticatedRequest.getGenre())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetBookByGenreActivity().handleRequest(request)
        );
    }
}
