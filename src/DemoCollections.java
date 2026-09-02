import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

        Set<String> hash = new HashSet<>(List.of("Celina", "Anna", "Bartek"));
        Set<String> linked = new LinkedHashSet<>(List.of("Celina", "Anna", "Bartek"));
        Set<String> tree = new TreeSet<>(List.of("Celina", "Anna", "Bartek"));


        System.out.println(hash); // kolejnosc dowolna
        System.out.println(linked); // jak wstawiono
        System.out.println(tree); // alfabetycznie

        TreeSet<Integer> prices = new TreeSet<>(List.of(10, 20, 30, 40, 50));

        System.out.println(prices.first()); // pierwszy
        System.out.println(prices.last()); // ostatni
        System.out.println(prices.floor(25)); // najwiekszy z <= 25
        System.out.println(prices.ceiling(25)); // najmniejszy z >= 25
        System.out.println(prices.headSet(30)); // mniejsze niz 30
        System.out.println(prices.tailSet(30)); // od 30 w gore
        System.out.println(prices.subSet(20, 40)); // od 20 wlacznie do 40 wylacznie


        Set<String> byLength = new TreeSet<>(Comparator.comparingInt(String::length));
        byLength.add("ABC");
        byLength.add("XYZ");

        System.out.println(byLength);



        // map

        Map<String, Integer> stock = new HashMap<>();
        stock.put("001", 5);
        stock.put("003", 1);
        stock.put("005", 3);

        int quantity = stock.get("001");
//        int missing = stock.get("NOTHING");
        int safe = stock.getOrDefault("NOTHING", 0);


        stock.containsKey("003");
        stock.remove("001");
        stock.size();

        stock.putIfAbsent("004", 3); // dodalo
        stock.putIfAbsent("003", 3); // ignoruje, jesli klucz istnieje

        stock.compute("004", (k, v) -> v == null ? 1 : v * 2);
        System.out.println(stock);

        stock.computeIfPresent("001", (k, v) -> v * 2);

        stock.computeIfAbsent("001", k -> 0);

        System.out.println(stock);

        stock.merge("003", 3, Integer::sum);
        System.out.println(stock);

        stock.replaceAll((k, v) -> v + 100);
        System.out.println(stock);

        stock.remove("003", 104);
        System.out.println(stock);

        // iteracja mapa
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        stock.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));

        int total = 0;
        for (int value : stock.values()) {
            total += value;
        }

//        antywzorzec
//        for (String key : stock.keySet()) {
//            System.out.println(stock.get(key));
//        }

        TreeMap<String, Integer> treeStock = new TreeMap<>();
        treeStock.put("001", 5);
        treeStock.put("005", 3);
        treeStock.put("003", 4);
        treeStock.put("007", 2);

        treeStock.firstKey(); // "001" najmniejszy klucz
        treeStock.lastKey(); // "007"
        treeStock.firstEntry(); // 001-5
        treeStock.lastEntry(); // 007-2
        System.out.println(treeStock.floorKey("005")); // 005 najwiekszy klucz z <= podanego
        System.out.println(treeStock.ceilingKey("004")); // 005 najmniejszy klucz >= podanego
        System.out.println(treeStock.lowerKey("003")); // 001 ściśle mniejszy
        System.out.println(treeStock.higherKey("001")); // 003 ściśle większy
        System.out.println(treeStock.headMap("005")); // {001=5, 003=4} klucze mniejsze od zadanego
        System.out.println(treeStock.tailMap("006")); // {007=2} od podanego w gore
        System.out.println(treeStock.subMap("001", "007")); // {001=5, 003=4, 005=3} od wlacznie do wylacznie
        System.out.println(treeStock.descendingMap()); // {007=2, 005=3, 003=4, 001=5} ta sama mapa z odwrotna kolejnoscia
        System.out.println(treeStock.pollFirstEntry()); // 001=5 zwraca i usuwa pierwszy wpis



        // queue
        Queue<String> elements = new ArrayDeque<>();
        elements.offer("1");
        elements.offer("2");
//        elements.add("2");
        elements.offer("3");

//        elements.remove();
//        elements.element();

        System.out.println(elements.peek()); //  1 podglad bez usuwania
        System.out.println(elements.poll()); //  1 pobrane i usuniete
        System.out.println(elements.poll()); //  2 pobrane i usuniete
        System.out.println(elements.size()); // 1


        // deque

        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("B");
        deque.addLast("C");
        deque.addFirst("A");

        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque.peek());


        // stos - lifo

        Deque<String> history = new ArrayDeque<>();
        history.push("1");
        history.push("2");
        history.push("3");
        System.out.println(history.pop());
        System.out.println(history.peek());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(List.of(10, 20, 5, 30));

        while(!pq.isEmpty()) {
            System.out.println(pq.poll() + " ");
        }


        // Collections

        List<Integer> nums = new ArrayList<>(List.of(3, 5, 20, 31, 14));
        Collections.sort(nums);
        System.out.println(nums);
        Collections.reverse(nums);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);
        System.out.println(Collections.min(nums));
        Collections.max(nums);
        Collections.frequency(nums, 5); // zliczanie wystapien
        Collections.swap(nums, 0, 1); // zamiana dwoch elementow
        Collections.emptyList();
        Collections.singleton(1);
        List<String> copies = Collections.nCopies(5, "x");
        List<String> newList = new ArrayList<>();
        newList.add("X1");
        Collections.disjoint(Set.of("A", "B"), Set.of("C", "D")); // true czy zbiory sa rozlaczne
        List<String> view = Collections.unmodifiableList(newList);
        List<String> copy = List.copyOf(newList);
        newList.add("X2");
        System.out.println(view);
        System.out.println(copy);


        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.putIfAbsent("001", 0);
        concurrentMap.merge("001", 1, Integer::sum);

        List<String> stringList = new CopyOnWriteArrayList<>();


















    }
}
