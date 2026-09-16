import java.math.BigDecimal;
import java.util.*;
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

        boolean isOutOfStock = products.stream()
                .anyMatch(p -> p.stock() == 0);

        boolean allRated = products.stream()
                .allMatch(p -> p.rating() > 0);

        boolean isFree = products.stream()
                .noneMatch(p -> p.price().signum() == 0);


        // reduce

        BigDecimal stockValue = products.stream()
                .map(p -> p.price().multiply(BigDecimal.valueOf(p.stock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Optional<BigDecimal> highestPrice = products.stream()
                .map(StreamProduct::price)
                .reduce(BigDecimal::max);


        // tak nie robimy bo tworzy nam nowy obiekt String przy kazdym kroku
        String reduceCodes = products.stream()
                .map(StreamProduct::sku)
                .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);


        List<String> mutable = products.stream()
                .map(StreamProduct::name)
                .collect(Collectors.toList());

        List<String> immutable = products.stream()
                .map(StreamProduct::name)
                .collect(Collectors.toUnmodifiableList());

        Set<String> categories = products.stream()
                .map(StreamProduct::category)
                .collect(Collectors.toSet());

        TreeSet<String> sortedCategory = products.stream()
                .map(StreamProduct::category)
                .collect(Collectors.toCollection(TreeSet::new));

        String joined = products.stream()
                .map(StreamProduct::name)
                .collect(Collectors.joining(", "));

        String bracketed = products.stream()
                .map(StreamProduct::sku)
                .collect(Collectors.joining(", ", "[", "]"));

        Map<String, String> bySku = products.stream()
                .collect(Collectors.toMap(StreamProduct::sku, StreamProduct::name));


        Map<String, String> duplicated = products.stream()
                .collect(Collectors.toMap(StreamProduct::category, StreamProduct::name, (a, b) -> a + "," + b));


        long count = products.stream()
                .collect(Collectors.counting());


        double priceSum = products.stream()
                .collect(Collectors.summingDouble(p -> p.price().doubleValue()));

        double avgRating = products.stream()
                .collect(Collectors.averagingDouble(StreamProduct::rating));

        DoubleSummaryStatistics stats = products.stream()
                .collect(Collectors.summarizingDouble(StreamProduct::rating));

        stats.getCount();
        stats.getMin();
        stats.getMax();
        stats.getSum();
        stats.getAverage();



        Map<String, List<StreamProduct>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category));


        groupedByCategory.forEach((cat, join) -> System.out.println(cat + " " + join.size()));



        Map<String, Long> countElements = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.counting()));
        // {ELEKTRONIKA=3, TELEFONY=2}

        Map<String, Double> averageRating = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.averagingDouble(StreamProduct::rating)));


        Map<String, List<String>> productNames = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.mapping(StreamProduct::name, Collectors.toList())));


        Map<String, Optional<StreamProduct>> mostExpensiveCat = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.maxBy(Comparator.comparing(StreamProduct::price))));

        Map<String, Optional<StreamProduct>> mostCheapestCat = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.minBy(Comparator.comparing(StreamProduct::price))));

        Map<String, String> descriptions = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.mapping(StreamProduct::name, Collectors.joining(", "))));


        Map<String, BigDecimal> valuePerCategory = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, Collectors.reducing(
                        BigDecimal.ZERO,
                        p -> p.price().multiply(BigDecimal.valueOf(p.stock())),
                        BigDecimal::add)));


        TreeMap<String, Long> groupedSorted = products.stream()
                .collect(Collectors.groupingBy(StreamProduct::category, TreeMap::new, Collectors.counting()));

        Map<String, Map<Boolean, List<String>>> nested = products.stream()
                .collect(Collectors.groupingBy(
                        StreamProduct::category,
                        Collectors.groupingBy(
                                p -> p.stock() > 0,
                                Collectors.mapping(StreamProduct::name, Collectors.toList())
                        )
                ));

        System.out.println(nested.get("ELEKTRONIKA"));


        Map<Boolean, List<StreamProduct>> partition = products.stream()
                .collect(Collectors.partitioningBy(p -> p.price().compareTo(new BigDecimal("1000")) > 0));


        Map<Boolean, Long> partitionCount = products.stream()
                .collect(Collectors.partitioningBy(p -> p.stock() > 0, Collectors.counting()));


        // IntStream, LongStream, DoubleStream



        int totalStock = products.stream()
                .mapToInt(StreamProduct::stock)
                .sum();

        System.out.println(totalStock);

        products.stream()
                .mapToInt(StreamProduct::stock)
                .average();

        products.stream()
                .mapToInt(StreamProduct::stock)
                .max();


        IntSummaryStatistics statsInt = products.stream()
                .mapToInt(StreamProduct::stock)
                .summaryStatistics();

        statsInt.getMin();
        statsInt.getAverage();

        List<Integer> stockList = products.stream()
                .mapToInt(StreamProduct::stock)
                .boxed()
                .toList();

        IntStream.rangeClosed(1, 3)
                .mapToObj(i -> "page " + i)
                .forEach(System.out::println);


        List<Integer> bigList = IntStream.rangeClosed(1, 10).boxed().toList();

        long start = System.nanoTime();
        long sum1 = bigList.stream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.printf("stream: %d ms%n", (System.nanoTime() - start) / 1_000_000);

        start = System.nanoTime();

        long sum2 = bigList.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();

        System.out.printf("parallelStream: %d ms%n", (System.nanoTime() - start) / 1_000_000);
    }
}
