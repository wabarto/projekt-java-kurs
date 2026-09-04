@FunctionalInterface
public interface InterestPolicy {
    double calculate(double balance, double amount);


    default double caulcatePrice() {
        return 0;
    }

    static double staticPrice() {
        return 0;
    }
}
