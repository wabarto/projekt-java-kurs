import java.math.BigDecimal;

public class Demo {
    static void doubleIt(int amount) {
        amount = amount * 2;
    }

    static void depositTo(BankAccount account) {
        account.deposit(BigDecimal.valueOf(1000));
    }

    static void replaceAccount(BankAccount account) {
        account = new SavingsAccount("PL99", BigDecimal.valueOf(999), BigDecimal.valueOf(0.03));
    }

    public static void main(String[] args) {
        int amount = 100;
        doubleIt(amount);
        System.out.println(amount);
        BankAccount account = new SavingsAccount("PL01", BigDecimal.valueOf(5000), BigDecimal.valueOf(0.03));
        depositTo(account);
        System.out.println(account.getBalance());
        replaceAccount(account);
        System.out.println(account.getBalance());
    }
}