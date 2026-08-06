import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        // Press Opt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome1!");
//        System.out.printf("Hello and welcome1!");
//
//        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//            System.out.printf("Hello and welcome!");
//
//            // Press Ctrl+D to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Cmd+F8.
//            System.out.println("create calculator and create feature and feature 3 and feature 4 and feature 6" + i);
//        }

        // tak nie robimy

//        BankAccount bankAccount = new BankAccount();
//        bankAccount.balance = 1000.0;
//        bankAccount.accountNumber = "123";
//
//        System.out.println("Balance: " + bankAccount.balance);
//
//        bankAccount.balance = bankAccount.balance - 5000.0;
//
//        System.out.println("Balance: " + bankAccount.balance);

        BankAccount bankAccount = new BankAccount("123", 1000);

        System.out.println(bankAccount.getBalance());

        final List<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        System.out.println(array);

        bankAccount.deposit(200);

        bankAccount.withdraw(400);

        System.out.println(bankAccount.getHistory());

        System.out.println(bankAccount.getHistory());

    }
}