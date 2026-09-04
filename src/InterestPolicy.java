@FunctionalInterface
public interface InterestPolicy {
    double calculate(double balance, double amount);


    default double caulcatePrice() {

    }

    static double staticPrice() {

    }
}
