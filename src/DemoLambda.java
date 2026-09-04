import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class DemoLambda implements InterestPolicy {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
                new Product("001", "laptop", new BigDecimal("3999.00"), 5),
                new Product("002", "mysz", new BigDecimal("49.00"), 5),
                new Product("003", "kawa", new BigDecimal("32.00"), 5)
        ));

        // lambda
        Comparator<Product> b = (x, y) -> x.price().compareTo(y.price());
        products.sort(b);
        products.forEach(p -> System.out.println(p.name()));
        products.forEach((Product p) -> System.out.println(p.name()));
        products.forEach((p) -> System.out.println(p.name()));
        // () -> "stala"

        Predicate<Product> valid = p -> {
            if (p.sku() == null) {
                return false;
            }

            return !p.name().isBlank();
        };

        BigDecimal limit = new BigDecimal("1000");
        Predicate<Product> expensive = p -> p.price().compareTo(limit) > 0;
//        limit = new BigDecimal("2000"); // final

        Product laptop = new Product("001", "laptop", new BigDecimal("3999.00"), 5);

        List<String> log = new ArrayList<>();
        Consumer<Product> logger = p -> log.add(p.sku());
        logger.accept(laptop);

        System.out.println(log);

//        log = new ArrayList<>();


        Function<Product, String> toName = p -> p.name(); // przeksztalca jedno w drugie
        System.out.println(toName.apply(laptop));

        Predicate<Product> inStock = p -> p.stock() > 0;
        System.out.println(inStock.test(laptop));


        Consumer<Product> print = p -> System.out.println(p.name());
        print.accept(laptop);

        Supplier<String> id = () -> limit.toString();
        System.out.println(id.get());


        BiFunction<BigDecimal, Integer, BigDecimal> total = (price, qty) -> price.multiply(BigDecimal.valueOf(qty));
        System.out.println(total.apply(new BigDecimal("20.00"), 5));


        Function<Integer, Integer> square = x -> x * x;
        square.apply(1);

        IntUnaryOperator squareFast = x -> x * x;
        squareFast.applyAsInt(1);

        ToIntFunction<Product> stockOf = Product::stock;
        System.out.println(stockOf.applyAsInt(laptop));

        IntPredicate isPositive = x -> x > 0;
        System.out.println(isPositive.test(-5));

        IntFunction<String> label = x -> "szt" + x;
        System.out.println(label.apply(7));


        // metoda statyczna
        Function<String, Integer> parse = Integer::parseInt;
        System.out.println(parse.apply("42"));

        // metoda konkretnego obiektu

        Consumer<String> print1 = System.out::println;
        print1.accept("test");

        // dowolnego obiektu danego typu
        Function<Product, String> toName1 = Product::name;
        Function<Product, String> toName2 = p -> p.name().toUpperCase();


        // konstruktor
        Supplier<List<Product>> newList = ArrayList::new;
        Function<String, BigDecimal> toMoney = BigDecimal::new;


        Function<BigDecimal, BigDecimal> withVat = p -> p.multiply(new BigDecimal("1.23"));
        Function<BigDecimal, BigDecimal> minusThen = p -> p.subtract(new BigDecimal("10"));

        withVat.andThen(minusThen).apply(new BigDecimal("100")); /// 123 - 10 = 113

        withVat.compose(minusThen).apply(new BigDecimal("100")); // (100-10) * 1.23 = 110.7


        Predicate<Product> cheap = p -> p.price().compareTo(new BigDecimal("100")) < 0;
        Predicate<Product> hasName = p -> p.name() != null && !p.name().isBlank();

        Predicate<Product> available = inStock.and(hasName); // inStock.or(hasName)
        Predicate<Product> outOfStock = inStock.negate();

        Predicate<String> notEmpty = Predicate.not(String::isEmpty);

        Consumer<Product> onChange = logger.andThen(print);
        onChange.accept(laptop);

    }

}
