import java.util.*;

public class DemoCollections {
    public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();
        numbers.add("001");
        numbers.add(0, "002");
        numbers.addAll(List.of("003", "004"));

        String first = numbers.get(0);
        int pos = numbers.indexOf("002"); // -1 jak nie ma
        boolean has = numbers.contains("005");

        numbers.set(0, "0000");
        numbers.remove("002");
        numbers.removeIf(s -> s.startsWith("00"));

        numbers.size();
        numbers.isEmpty();

        List<Integer> quantities = new ArrayList<>(List.of(10, 20, 30)); // List.of(10, 20, 30))
        List<Integer> quantities1 = List.of(10, 20, 30);
        quantities.remove(1);
        quantities.remove(Integer.valueOf(10));

        List.copyOf(List.of(10, 20, 30)); // staly rozmiar, immutable

        Arrays.asList(List.of(10, 20, 30)); // mozna uzywac set ale nie mozna dodawac



        // set

        Set<String> categories = new HashSet<>();
        categories.add("001");
        categories.add("002");
        categories.add("003");
        categories.add("001");
        categories.add("A");

        System.out.println(categories.size());

        List<String> duplicates = List.of("A", "B", "A");
        Set<String> uniques = new HashSet<>(duplicates);
        System.out.println(uniques);

        Set<String> sum = new HashSet<>(categories);
        sum.addAll(uniques);

        Set<String> common = new HashSet<>(categories);
        common.retainAll(uniques);
        System.out.println(common);

        Set<String> temp = new HashSet<>(categories);
        temp.removeAll(uniques);
        System.out.println(temp);



    }
}
