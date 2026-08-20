import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
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

        BankAccount bankAccount1 = new SavingsAccount("123", BigDecimal.valueOf(1000), BigDecimal.valueOf(1.0));

        System.out.println(bankAccount1.getBalance());

        final List<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        System.out.println(array);

        bankAccount1.deposit(BigDecimal.valueOf(200));

        bankAccount1.withdraw(BigDecimal.valueOf(400));

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
                new SavingsAccount("PL02", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.3)),
                new CheckingAccount("PL03", BigDecimal.valueOf(2000), BigDecimal.valueOf(5.0)),
                new SavingsAccount("PL04", BigDecimal.valueOf(12000), BigDecimal.valueOf(0.045))
        );

        for (BankAccount account : accounts) {
            System.out.println(account.getDescription());
        }

        BigDecimal sum = BigDecimal.ZERO;

        for (BankAccount account : accounts) {
            sum = sum.add(account.getBalance());
        }

        System.out.println(sum);


        BankAccount account = new SavingsAccount("PL003", BigDecimal.valueOf(10000), BigDecimal.valueOf(0.3));
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


        BankAccount a = new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.03));
        BankAccount b = new CheckingAccount("PL02", BigDecimal.valueOf(1000), BigDecimal.valueOf(5.0));

        new TransferService(new SmsNotifier("+48111111111")).transfer(a, b, BigDecimal.valueOf(500));
        new TransferService(new EmailNotifier("xyz@example.com")).transfer(a, b, BigDecimal.valueOf(200));
        new TransferService(new NoOpNotifier()).transfer(a, b, BigDecimal.valueOf(100));


        List<String> list = new LinkedList<>();
        LinkedList<String> list1 = new LinkedList<>();



        String text = "123 %s".formatted("123");
        String text1 = String.format("123 %s", "123");
        String text2 = "123";

//        InterestPolicy policy = (balance, amount) -> balance + amount;


        new GenericExample<String>("Hello");
        new GenericExample<>("Hello");


        System.out.println(findMax(List.of(3, 17, 8)));
        System.out.println(findMax(List.of("PL01", "PL07")));

        List<SavingsAccount> savings = List.of(new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.3)));
        balanceSum(savings);
        // SavingsAccount == BankAccount
        // List<SavingsAccount> == List<BankAccount>
        // List<? extends BankAccount> - lista czegos co jest kontem, mozna czytac BankAccount, nie mozna nic dodac
        // List<? super SavingsAccount> - list konta oszczednosciowego lub jego nadtypu, mozna dodawac
        // PECS - Producer Extends, Consumer Super - czytasz z kolekcji - extends, dopisujesz - super


        Repository<BankAccount, String> repo = new InMemoryAccountRepository();
        repo.save(new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.3)));
        repo.save(new CheckingAccount("PL02", BigDecimal.valueOf(2000), BigDecimal.valueOf(3)));


        repo.findById("PL01")
                .ifPresentOrElse(
                        bankAccount -> System.out.println("Found"),
                        () -> System.out.println("Not found")
                );


        // enum

        account.setStatus(AccountStatus.BLOCKED);

        AccountStatus status = AccountStatus.BLOCKED;


        System.out.println(status);
        System.out.println(status.name());
        System.out.println(status.ordinal());


        AccountStatus.valueOf("ACTIVE");
        Arrays.toString(AccountStatus.values());

        EnumSet<AccountStatus> active = EnumSet.of(AccountStatus.ACTIVE, AccountStatus.BLOCKED);

        EnumMap<AccountStatus, Integer> count = new EnumMap<>(AccountStatus.class);
        count.put(AccountStatus.ACTIVE, 111);


        // record

        Statement statement1 = new Statement("PL01", "savings", BigDecimal.valueOf(5000));

        System.out.println(statement1.accountNumber());
        System.out.println(statement1);

        Statement statement2 = new Statement("PL01", "savings", BigDecimal.valueOf(5000));

        System.out.println(statement1.equals(statement2)); // true
        System.out.println(statement1 == statement2); // false


        // var
        var bankAccounts = new ArrayList<BankAccount>();
        var number = "PL01";

        // text block
        String header = "123\n123";

        String newHeader = """
                123
                123
                """;

        // pattern matching
        Object obj = repo.findById("PL01").orElseThrow();

        if (obj instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) obj;
            savingsAccount.addInterest();
        }

        if (obj instanceof SavingsAccount savingsAccount && savingsAccount.getBalance().compareTo(BigDecimal.valueOf(100)) > 0) {
            savingsAccount.addInterest();
        }


        // setAccessible
        for (Field field : a.getClass().getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + "=" + field.get(a));
        }


        // BigDecimal

        BankAccount newSavingsAccount = new SavingsAccount("PL02", BigDecimal.valueOf(0), BigDecimal.valueOf(0.3));

        for (int i = 0; i < 10; i++) {
            newSavingsAccount.deposit(BigDecimal.valueOf(0.10));
        }

        System.out.println(newSavingsAccount.getBalance());
        System.out.println(newSavingsAccount.getBalance().compareTo(BigDecimal.valueOf(1.0)) == 0);

        // 0001100....

        System.out.println(new BigDecimal(0.1)); // tak nie robimy
        System.out.println(new BigDecimal("0.1"));
        BigDecimal.valueOf(0.1);


        System.out.println(new BigDecimal("10").divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP));

        // 5.0 - skala 1
        // 5.00 - skala 2

        BigDecimal aBigDecimal = new BigDecimal("5.0");
        BigDecimal bBigDecimal = new BigDecimal("5.00");

        System.out.println(aBigDecimal.equals(bBigDecimal));
        System.out.println(aBigDecimal.compareTo(bBigDecimal) == 0);

        // String

        String s = " PL01 PL02 PL03  ";
        s.toUpperCase();
        System.out.println(s);

        String upper = s.toUpperCase();

        System.out.println(s.strip());
        s.isBlank();
        s.isEmpty();
        s.contains("123");
        System.out.println(s.indexOf("xy"));
        System.out.println(s.substring(1));
        s.replace(" ", "");
        String[] arrayString = s.strip().split(" ");
        System.out.println(arrayString);
        for (String word : arrayString) {
            System.out.println(word);
        }

        long start = System.nanoTime();

        String str = "";

        for (int i = 0; i < 50000; i++) {
            str += i;
        }

        long time1 = System.nanoTime() - start;

        start = System.nanoTime();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sb.append(i);
        }

        String stringSb = sb.toString();

        long time2 = System.nanoTime() - start;

        System.out.println(time1);
        System.out.println(time2);

        // sort
        List<BankAccount> sortAccounts = new ArrayList<>(repo.findAll());
        Collections.sort(sortAccounts);


        // najbogatsi na gorze
        sortAccounts.sort(Comparator.comparing(BankAccount::getBalance).reversed());

        // typ konta, saldo malejaco, numerze

        sortAccounts.sort(
                Comparator.comparing(BankAccount::getAccountType)
                        .thenComparing(Comparator.comparing(BankAccount::getBalance).reversed())
                        .thenComparing(BankAccount::getAccountNumber)
        );


//        sortAccounts.sort((a, b) -> Integer.compare(a.getTransactionCount(), b.getTransactionCount()));


        // lombok

        TransferRequest request = TransferRequest.builder()
                .fromAccount("PL01")
                .toAccount("PL02")
                .build();

        Customer customer = new Customer();
        customer.getEmail();
        customer.setEmail("test@gmail.com");

    }

    static String description(AccountStatus status) {
        return switch(status){
            case ACTIVE -> "Aktywne";
            case BLOCKED -> "Zablokowane";
            case CLOSED -> "Zamkniete";
        };
    }

//    static double balanceSum(List<BankAccount> accounts) {
//        BigDecimal sum = BigDecimal.ZERO;
//        for (BankAccount acc : accounts) {
//            sum = sum.add(acc.getBalance());
//        }
//        return sum;
//    }

    static BigDecimal balanceSum(List<? extends BankAccount> accounts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BankAccount acc : accounts) {
            sum = sum.add(acc.getBalance());
        }
        return sum;
    }

    static void openAccount(List<? super SavingsAccount> list) {
        list.add(new SavingsAccount("PL05", BigDecimal.valueOf(3000), BigDecimal.valueOf(0.3)));
    }

    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

}