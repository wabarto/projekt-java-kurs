public class ContentTypeHandler extends RequestHandler {
    @Override
    public void handle(ApiRequest request) {
        if (!request.isJson()) {
            System.out.println("REJECTED");
            return;
        }

        passToNext(request);
    }
}
