public abstract class RequestHandler {

    RequestHandler next;

    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(ApiRequest request);


    void passToNext(ApiRequest request) {
        if (next != null) {
            next.handle(request);
        }
    }
}
