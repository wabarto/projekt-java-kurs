import java.util.ArrayList;
import java.util.LinkedList;
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

        BankAccount bankAccount1 = new SavingsAccount("123", 1000, 1.0);

        System.out.println(bankAccount1.getBalance());

        final List<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        System.out.println(array);

        bankAccount1.deposit(200);

        bankAccount1.withdraw(400);

        System.out.println(bankAccount1.getHistory());

        System.out.println(bankAccount1.getHistory());

//        BankAccount bankAccount2 = BankAccount.createEmpty("123", 5.0)
//                .addBalance(5.0)
//                .addBalance(6.0);

//        System.out.println(bankAccount1);
//        System.out.println(bankAccount1 == bankAccount2);
//        System.out.println(bankAccount1.equals(bankAccount2));
//        System.out.println(bankAccount1.getClass().getName());
//        System.out.println(bankAccount1.getClass().getSimpleName());


        List<BankAccount> accounts = List.of(
                //new BankAccount("PL001", 10000), // klasa abstrakcyjna
                new SavingsAccount("PL02", 5000, 0.3),
                new CheckingAccount("PL03", 2000, 5.0),
                new SavingsAccount("PL04", 12000, 0.045)
        );

        for (BankAccount account : accounts) {
            System.out.println(account.getDescription());
        }

        double sum = 0;

        for (BankAccount account : accounts) {
            sum += account.getBalance();
        }

        System.out.println(sum);


        BankAccount account = new SavingsAccount("PL003", 10000, 0.3);
        account.getDescription();
//        account.addInterest();  -> tak sie nie da bo kompilator patrzy na typ referencji -> BankAccount



        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.record("+500");
        transactionHistory.record("-200");
//        transactionHistory.remove(0);
//        transactionHistory.set(0, "+999"); // niepoprawna dziedziczenie
//        transactionHistory.clear();


        // downcasting - rzutowanie w dol hierarchii

        if (account instanceof SavingsAccount s) {
            s.addInterest();
        }


//        CheckingAccount checkingAccount = (CheckingAccount) account;


        BankAccount a = new SavingsAccount("PL01", 5000, 0.03);
        BankAccount b = new CheckingAccount("PL02", 1000, 5.0);

        new TransferService(new SmsNotifier("+48111111111")).transfer(a, b, 500);
        new TransferService(new EmailNotifier("xyz@example.com")).transfer(a, b, 200);
        new TransferService(new NoOpNotifier()).transfer(a, b, 100);


        List<String> list = new LinkedList<>();
        LinkedList<String> list1 = new LinkedList<>();



        String text = "123 %s".formatted("123");
        String text1 = String.format("123 %s", "123");
        String text2 = "123";

//        InterestPolicy policy = (balance, amount) -> balance + amount;


    }
}