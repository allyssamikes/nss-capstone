package lambda;

import activity.request.GetListRequest;
import activity.result.GetCurrentlyReadingResult;
import org.gradle.process.internal.worker.RequestHandler;

import javax.naming.Context;

public class GetCurrentlyReadingListLambda
        extends LambdaActivityRunner<GetListRequest, GetCurrentlyReadingResult>
        implements RequestHandler<LambdaRequest<GetListRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetListRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetListRequest.builder()
                                .withUserId(path.get("userId"))
                                .build()),

                (request, serviceComponent) ->
                        serviceComponent.provideGetCurrentlyReadingActivity().handleRequest(request)
        );
    }
}
