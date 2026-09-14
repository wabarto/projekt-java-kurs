import java.util.List;

public record Order(String id, List<StreamProduct> items) {
}
