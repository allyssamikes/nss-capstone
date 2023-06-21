package capstoneservice.lambda;

import capstoneservice.activity.request.GetBookByAuthorRequest;
import capstoneservice.activity.request.GetBookRequest;
import capstoneservice.activity.result.GetBookByAuthorResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class GetBookByAuthorLambda  extends LambdaActivityRunner<GetBookByAuthorRequest, GetBookByAuthorResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetBookByAuthorRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetBookByAuthorRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetBookByAuthorRequest.builder()
                                .withAuthor(path.get("author"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetBookByAuthorActivity().handleRequest(request)
        );
    }
}
