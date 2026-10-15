@FunctionalInterface
public interface DiscountStrategy {
    double apply(double price, int quantity);
}
