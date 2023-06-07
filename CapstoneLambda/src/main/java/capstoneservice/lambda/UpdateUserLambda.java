package capstoneservice.lambda;

import capstoneservice.activity.request.UpdateUserRequest;
import capstoneservice.activity.result.UpdateUserResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class UpdateUserLambda
        extends LambdaActivityRunner<UpdateUserRequest, UpdateUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateUserRequest unauthenticatedRequest = input.fromBody(UpdateUserRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateUserRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withName(unauthenticatedRequest.getName())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateUserActivity().handleRequest(request)
        );
    }
}
