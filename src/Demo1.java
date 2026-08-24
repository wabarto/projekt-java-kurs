import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    private static final List<String> ENTRIES = new ArrayList<>();
    public static void main(String[] args) { // 1
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



    }

    static void withdraw(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal before = bankAccount.getBalance(); // 5
        bankAccount.withdraw(amount); // 6
    }
}
