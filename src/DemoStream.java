import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DemoStream {
    public static void main(String[] args) {
        List<StreamProduct> products = List.of(
                new StreamProduct("ELEC-001", "Laptop Dell", "ELEKTRONIKA", new BigDecimal("3499.00"), 5, 4.6),
                new StreamProduct("ELEC-002", "Monitor 27", "ELEKTRONIKA", new BigDecimal("1299.00"), 12, 4.3),
                new StreamProduct("ELEC-003", "Laptop HP", "ELEKTRONIKA", new BigDecimal("2999.00"), 0, 4.1),
                new StreamProduct("PHON-001", "Iphone 15", "TELEFONY", new BigDecimal("4799.00"), 8, 4.8),
                new StreamProduct("PHON-002", "Samsung S24", "TELEFONY", new BigDecimal("3299.00"), 15, 4.5),
                new StreamProduct("ACCE-001", "Klawiatura MX", "AKCESORIA", new BigDecimal("499.00"), 40, 4.7),
                new StreamProduct("ACCE-002", "Mysz Logitech", "AKCESORIA", new BigDecimal("199.00"), 60, 4.4),
                new StreamProduct("ACCE-003", "Podkładka4", "AKCESORIA", new BigDecimal("49.00"), 3, 3.9),
                new StreamProduct("ACCE-003", "Podkładka1", "AKCESORIA", new BigDecimal("49.00"), 3, 3.9),
                new StreamProduct("ACCE-003", "Podkładka2", "AKCESORIA", new BigDecimal("49.00"), 3, 3.9),
                new StreamProduct("ACCE-003", "Podkładka3", "AKCESORIA", new BigDecimal("49.00"), 3, 3.9),
                new StreamProduct("AUDI-001", "Sluchawki sony", "AUDIO", new BigDecimal("899.00"), 24, 4.9),
                new StreamProduct("AUDI-002", "JBL", "AUDIO", new BigDecimal("349.00"), 18, 4.2)
        );

        Stream<String> pipeline = products.stream()
                .filter(p -> {
                    System.out.println("filter" + p.sku());
                    return p.stock() > 0;
                })
                .map(p -> {
                    System.out.println("map" + p.sku());
                    return p.name();
                });

        List<String> names = pipeline.toList();
        System.out.println(names);


        // z kolekcji
        Optional<StreamProduct> first = products.stream()
                .filter(p -> {
                    System.out.println("check" + p.sku());
                    return p.price().compareTo(new BigDecimal("1000")) > 0;
                })
                .findFirst();

        // z tablicy
        String[] codes = {"ELEC-001", "ELEC-002"};
        Arrays.stream(codes).forEach(System.out::println);

        // z konkretnych wartosci
        Stream.of("ELEKTRONIKA", "AUDIO").forEach(System.out::println);

        IntStream.range(1, 5).forEach(System.out::println); // wyklucza koniec 1234
        IntStream.rangeClosed(1, 5).forEach(System.out::println); // zawiera koniec, 12345


        // z ciagow

        Stream.iterate(1, n -> n * 2)
                .limit(8)
                .forEach(n -> System.out.println(n + " "));

        Stream.generate(Math::random)
                .limit(3)
                .forEach(System.out::println);


        // filter

        products.stream()
                .filter(p -> p.rating() >= 4.5)
                .forEach(p -> System.out.println(p.name()));

        // map

        products.stream()
                .map(p -> p.name().length())
                .forEach(System.out::println);

        // distinct

        products.stream()
                .map(StreamProduct::name)
                .distinct()
                .forEach(System.out::println);

        // sorted, limit
        products.stream()
                .map(StreamProduct::name)
                .sorted()
                .forEach(System.out::println);

        products.stream()
                .sorted(Comparator.comparing(StreamProduct::price).reversed())
                .limit(3)
                .forEach(p -> System.out.println(p.name()));


        // skip

        products.stream()
                .skip(7)
                .forEach(System.out::println);


        List<Order> orders = List.of(
                new Order("ORD-1", List.of(products.get(0), products.get(5))),
                new Order("ORD-2", List.of(products.get(3), products.get(2))),
                new Order("ORD-2", List.of(products.get(7), products.get(6)))
        );

        List<String> allItems = orders.stream()
                .flatMap(o -> o.items().stream())
                .map(StreamProduct::name)
                .toList();

        // peek

        List<String> result = products.stream()
                .peek(p -> System.out.println(p.sku()))
                .filter(p -> p.stock() > 20)
                .peek(p -> System.out.println(p.name()))
                .map(StreamProduct::name)
                .peek(System.out::println)
                .toList();


        // toList

        List<String> results = products.stream()
                .map(StreamProduct::name)
                .toList();

//        results.add("123");


        // count

        long available = products.stream()
                .filter(p -> p.stock() > 0)
                .count();

        // forEach
        products.stream()
                .filter(p -> p.stock() > 0)
                .forEach(System.out::println);

        // findFirst
        String cheapest = products.stream()
                .filter(p -> p.category().equals("AUDIO"))
                .findFirst()
                .map(StreamProduct::name)
                .orElse("nothing");


        // min/max

        Optional<StreamProduct> mostExpensive = products.stream()
                .max(Comparator.comparing(StreamProduct::price));


        Optional<StreamProduct> worstRated = products.stream()
                .max(Comparator.comparingDouble(StreamProduct::rating));


        // findFirst vs findAny

        products.stream()
                .filter(p -> p.category().equals("AKCESORIA"))
                .findFirst()
                .ifPresent(p -> System.out.println(p.name()));


        products.parallelStream()
                .filter(p ->  {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return p.category().equals("AKCESORIA");
                })
                .findAny()
                .ifPresent(p -> System.out.println(p.name() + "parallel"));


        // anyMatch, allMatch, noneMatch

        // reduce












    }
}
