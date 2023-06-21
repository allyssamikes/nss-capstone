package capstoneservice.lambda;

import capstoneservice.activity.request.DeleteUserRequest;
import capstoneservice.activity.result.DeleteUserResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class DeleteUserLambda
        extends LambdaActivityRunner<DeleteUserRequest, DeleteUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteUserRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                            DeleteUserRequest.builder()
                                    .withUserId(path.get("userId"))
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteUserActivity().handleRequest(request)
        );
    }
}
