import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesDemo {
    public static void main(String[] args) throws IOException {
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
        Path file = Path.of("date", "raporty", "sprzedaz.csv");
        System.out.println(file);

        System.out.println(file.getFileName()); // sprzedaz.csv
        System.out.println(file.getParent()); // date/raporty
        System.out.println(file.toAbsolutePath()); // /Users/wabarto/projekt-kurs-java-1/date/raporty/sprzedaz.csv
        System.out.println(file.getNameCount()); // 3

        Path dir = Path.of("date");
        Path result = dir.resolve("output.json"); // date/output.json

        Path from = Path.of("/home/user");
        Path to = Path.of("/home/user/date/file.txt");
        System.out.println(from.relativize(to)); // date/file.txt


        Files.createDirectories(Path.of("date"));
        Files.writeString(Path.of("date/produkty.txt"), "Laptop Dell\nMysz Logitech\nMonitor 27\n");


        String whole = Files.readString(Path.of("date/produkty.txt"));
        System.out.println(whole.lines().count());

        List<String> lines = Files.readAllLines(Path.of("date/produkty.txt"));
        System.out.println(lines.get(0));

        try (Stream<String> stream = Files.lines(Path.of("date/produkty.txt"))) {
            long count = stream
                    .count();
            System.out.println(count);
        }


        Files.writeString(Path.of("date/produkty.txt"), "add\n", StandardOpenOption.APPEND);


        List<String> rows = products.stream()
                .map(p -> p.sku() + " " + p.price())
                .toList();

        Files.write(Path.of("date/produkty.txt"), rows);

        String text = Files.readString(Path.of("date/produkty.txt"), Charset.forName("windows-1250"));


        Files.exists(Path.of("date/report.txt"));
        Files.isDirectory(Path.of("date"));
        Files.isRegularFile(Path.of("date/report.txt"));
        Files.size(Path.of("date/produkty.txt"));


//        Files.createDirectory(Path.of("123/example"));
//        Files.createDirectories(Path.of("123/date/first/text"));


//        Files.copy(Path.of("date/produkty.txt"), Path.of("date/snapshot.txt"), StandardCopyOption.REPLACE_EXISTING);
//
//        Files.move(Path.of("date/produkty.txt"), Path.of("date/moved.txt"), StandardCopyOption.REPLACE_EXISTING);

//        Files.delete(Path.of("date/moved.txt"));
//        Files.deleteIfExists(Path.of("date/moved.txt"));


        try (Stream<Path> entries = Files.list(Path.of("date"))) {
            entries
                    .map(Path::getFileName)
                    .forEach(System.out::println);
        }

        System.out.println();


        try (Stream<Path> entries = Files.walk(Path.of("date"), 2)) {
            entries
                    .map(Path::getFileName)
                    .forEach(System.out::println);
        }



        try (BufferedReader reader = Files.newBufferedReader(Path.of("date/produkty.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }


        try (Stream<String> streamLines = Files.lines(Path.of("date/produkty.txt"))) {
            streamLines.forEach(System.out::println);
        }


        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("date/result.txt"))) {
            writer.write("example");
            writer.newLine();
        }

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Path.of("date/table.txt")))) {
            pw.printf("%-20s %10s%n", "PRODUKT", "CENA");
            pw.printf("-".repeat(30));
            for (StreamProduct p : products) {
                pw.printf("%-20s %10.2f%n", p.name(), p.price());
            }
        }



        try (InputStream in = Files.newInputStream(Path.of("date/zdjecie.jpg"));
             OutputStream out = Files.newOutputStream(Path.of("date/snapshot.png"))) {
            byte[] bufor = new byte[8192];
            int read;
            while ((read = in.read(bufor)) != -1) {
                out.write(bufor, 0, read);
            }
        }





    }
}
