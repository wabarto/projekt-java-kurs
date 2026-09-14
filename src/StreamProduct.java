import java.math.BigDecimal;

public record StreamProduct(String sku, String name, String category, BigDecimal price, int stock, double rating) {
}
