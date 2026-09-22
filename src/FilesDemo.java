import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesDemo {

    static ObjectMapper objectMapper = new ObjectMapper();
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


        Files.writeString(Path.of("date/produkty.csv"), """
                sku,name,category,price,stock,rating
                ELEC-001,"Laptop, Dell",ELEKTRONIKA,3499.00,5,4.6
                ELEC-002,Mysz Logitech,ELEKTRONIKA,150.00,3,4.6
                ELEC-003,Popsuty,ELEKTRONIKA,150.00
                """);


        List<StreamProduct> loaded = loadProducts(Path.of("date/produkty.csv"));
        System.out.println(loaded);

        exportProducts(loaded, Path.of("date/eksport.csv"));

        try (CSVReader reader = new CSVReaderBuilder(new FileReader("date/produkty.csv"))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .build())
                .build();
             CSVWriter writer = new CSVWriter(new FileWriter("date/output.csv"))) {
            String[] row;

            while ((row = reader.readNext()) != null) {
                writer.writeNext(row);
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


        StreamProduct laptop = products.get(0);
        String json = objectMapper.writeValueAsString(laptop);
        System.out.println(json);


        String pretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(laptop);
        System.out.println(pretty);



        String list = objectMapper.writeValueAsString(products);
        System.out.println(list);

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(Path.of("date/produkty.json").toFile(), products);


        String input = """
                {"sku":"ELEC-001","name":"Laptop Dell","category":"ELEKTRONIKA","price":3499.00,"stock":5,"rating":4.6}
                """;
        StreamProduct parsed = objectMapper.readValue(input, StreamProduct.class);

        System.out.println(parsed);

        List<StreamProduct> fromFile = objectMapper.readValue(Path.of("date/produkty.json").toFile(), new TypeReference<>() {
        });



        System.out.println(fromFile);

        String spcInput = """
                {"sku":"ELEC-001","name":"Laptop Dell"}
                """;

        StreamProductClass spc = objectMapper.readValue(spcInput, StreamProductClass.class);

        System.out.println(spc);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // "2026-09-21", nie 18735782

        StreamProductClass spc1 = new StreamProductClass("1", "name", LocalDate.of(2026, 11, 21));

        System.out.println(objectMapper.writeValueAsString(spc1));


        String response = """
                {
                    "status": "ok",
                    "date": {
                        "product": { "id": 1, "name": "Laptop", "price": 3499.00 },
                        "tags": ["nowosc", "promocja", "polecane"]
                        },
                    "meta": { "total": 1, "page": 1 }
                    }
                """;

        JsonNode root = objectMapper.readTree(response);

        System.out.println(root.get("status").asText());
        System.out.println(root.get("meta").get("total").asInt());

        System.out.println(root.path("date").path("product").path("name").asText());

        String missing = root.path("date").path("notexists").path("name").asText();
        System.out.println(missing);

        JsonNode tags = root.path("date").path("tags");

        if (tags.isArray()) {
            tags.forEach(t -> System.out.println(t.asText()));
        }

        System.out.println(root.has("status"));
        System.out.println(root.path("date").path("product").isObject());


        StreamProduct jsonProduct = objectMapper.treeToValue(root.path("date").path("product"), StreamProduct.class);

        System.out.println(jsonProduct);


        ObjectNode node = objectMapper.createObjectNode();
        node.put("status", "ok");
        node.put("count", products.size());

        ArrayNode names = objectMapper.createArrayNode();
        products.forEach(p -> names.add(p.name()));
        node.set("products", names);


        System.out.println(objectMapper.writeValueAsString(node));

        Payment payment = new CardPayment(new BigDecimal("199.00"), "31223232");
        System.out.println(objectMapper.writeValueAsString(payment));

        String cardInput = """
                {
                  "type" : "card",
                  "amount" : 199.00,
                  "cardNumber" : "31223232"
                }
                """;

        Payment parsedPayment = objectMapper.readValue(cardInput, Payment.class);
        System.out.println(parsedPayment.getClass().getSimpleName());



    }


    static Optional<StreamProduct> parseLine(String line) {
        String[] fields = line.split(",", -1);

        if (fields.length != 6) {
            return Optional.empty();
        }

        try {
            return Optional.of(new StreamProduct(
                    fields[0].trim(),
                    fields[1].trim(),
                    fields[2].trim(),
                    new BigDecimal(fields[3].trim()),
                    Integer.parseInt(fields[4].trim()),
                    Double.parseDouble(fields[5].trim())
            ));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    static List<StreamProduct> loadProducts(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines.skip(1)
                    .filter(l -> !l.isBlank())
                    .map(FilesDemo::parseLine)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    static void exportProducts(List<StreamProduct> list, Path file) throws IOException {
        String csv = Stream.concat(
                Stream.of("sku,name,category,price,stock,rating"),
                list.stream()
                        .map(p -> String.join(",", p.sku(), p.name(), p.category(), p.price().toString(), String.valueOf(p.stock()), String.valueOf(p.rating())))
        ).collect(Collectors.joining("\n"));

        Files.writeString(file, csv);
    }

}
