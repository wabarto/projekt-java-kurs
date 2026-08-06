import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final List<String> history = new ArrayList<>();

    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }

        if (balance < 0) {
            throw new IllegalArgumentException("Balance has to be > 0");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
        history.add("Initial balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount has to be > 0");
        }

        balance += amount;

    }

    public List<String> getHistory() {
        return List.copyOf(history);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount has to be > 0");
        }

        if (amount > balance) {
            throw new IllegalStateException("Incorrect amount");
        }

        balance -= amount;

    }

}
