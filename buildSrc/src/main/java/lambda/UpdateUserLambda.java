package lambda;

import activity.request.UpdateUserRequest;
import activity.result.UpdateUserResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

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
