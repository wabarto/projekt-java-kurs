public class GenericExample<T> {
    private final T content;

    public GenericExample(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
