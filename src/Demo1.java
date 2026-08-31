import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    private static final List<String> ENTRIES = new ArrayList<>();
    public static void main(String[] args) throws IOException { // 1
        BigDecimal amount = new BigDecimal("500"); // 2
        BankAccount account = new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.03)); // 3
        withdraw(account, amount); // 4
        System.out.println("end"); // 7


        List<BankAccount> accounts = new ArrayList<>();
        int i = 0;
//        while (true) {
//            new SavingsAccount("PL" + i++, BigDecimal.valueOf(5000), BigDecimal.valueOf(0.03));
//        }

        BankAccount old = new SavingsAccount("PL00", BigDecimal.valueOf(100), BigDecimal.valueOf(0.03));
        BankAccount newAccount = new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.03));
        newAccount.deposit(new BigDecimal("500"));
        old = null;

        String a = "PL01";
        String b = "PL01";
        String c = new String("PL01");
        String d = "PL" + "01";
        String prefix = "PL";
        String e = prefix + "01";


        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(a == e);


        // checked
//        String content = Files.readString(Path.of("text.txt"));

        // unchecked
        old = null;
//        old.getBalance();


//        try {
//            Files.readString(Path.of("text.txt"));
//            old.withdraw(new BigDecimal("5000"));
//        }
//        catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        catch (InsufficientFundsException ex) {
//            System.out.println(ex.getMessage());
//        }
//        catch (IllegalStateException | IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            System.out.println("finally");
//        }


        try {
            DriverManager.getConnection("123");
        }
        catch (SQLException ex) {
            throw new BankException("Blad polaczenia z baza danych", "DB_ERROR");
        }


        System.out.println(tricky());

        try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
             BufferedReader reader1 = new BufferedReader(new FileReader("text.txt"));
             Audit audit = new Audit()) {
            System.out.println(reader.readLine());
        }
    }


    static int tricky() {
        try {
            throw new RuntimeException("error");
        } finally {
            return 1;
        }
    }

    static void withdraw(BankAccount bankAccount, BigDecimal amount) throws IOException {
        BigDecimal before = bankAccount.getBalance(); // 5
        bankAccount.withdraw(amount); // 6
    }
}
