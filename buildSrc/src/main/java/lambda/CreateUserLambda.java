package lambda;

import activity.request.CreateUserRequest;
import activity.result.CreateUserResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class CreateUserLambda
        extends LambdaActivityRunner<CreateUserRequest, CreateUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateUserRequest unauthenticatedRequest = input.fromBody(CreateUserRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateUserRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withName(unauthenticatedRequest.getName())
                                    .withToReadList(unauthenticatedRequest.getToReadList())
                                    .withToWatchList(unauthenticatedRequest.getToWatchList())
                                    .withCurrentlyReading(unauthenticatedRequest.getCurrentlyReading())
                                    .withCurrentlyWatching(unauthenticatedRequest.getCurrentlyWatching())
                                    .withReadList(unauthenticatedRequest.getReadList())
                                    .withWatchedList(unauthenticatedRequest.getWatchedList())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserActivity().handleRequest(request)
        );
    }
}

