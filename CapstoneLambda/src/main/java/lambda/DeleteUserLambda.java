package lambda;

import activity.request.DeleteUserRequest;
import activity.result.DeleteUserResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class DeleteUserLambda
        extends LambdaActivityRunner<DeleteUserRequest, DeleteUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    DeleteUserRequest unauthenticatedRequest = input.fromBody(DeleteUserRequest.class);
                    return input.fromUserClaims(claims ->
                            DeleteUserRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteUserActivity().handleRequest(request)
        );
    }
}
