public class AuthHandler extends RequestHandler {
    @Override
    public void handle(ApiRequest request) {
        if (!request.hasToken()) {
            System.out.println("401");
            return;
        }

        passToNext(request);
    }
}
