package capstoneservice.lambda;

import capstoneservice.activity.request.GetBookByAuthorRequest;
import capstoneservice.activity.result.GetBookByAuthorResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetBookByAuthorLambda  extends LambdaActivityRunner<GetBookByAuthorRequest, GetBookByAuthorResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetBookByAuthorRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetBookByAuthorRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                            GetBookByAuthorRequest.builder()
                                    .withAuthor(query.get("author"))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetBookByGenreActivity().handleRequest(request)
        );
    }
}
