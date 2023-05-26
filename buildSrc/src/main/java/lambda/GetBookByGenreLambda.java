package lambda;

import activity.request.GetBookByGenreRequest;
import activity.result.GetBookByGenreResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class GetBookByGenreLambda  extends LambdaActivityRunner<GetBookByGenreRequest,GetBookByGenreResult>
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
