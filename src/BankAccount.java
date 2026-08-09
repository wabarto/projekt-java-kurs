import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {

    private static int accountsCreated = 0;

    private static final double MIN_INITIAL_BALANCE = 0.0;

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
        accountsCreated++;
    }


    private BankAccount(String accountNumber, double balance, String history) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static BankAccount createEmpty(String accountNumber, double balance) {
//        return new BankAccount(accountNumber, balance);
        return new BankAccount(accountNumber, balance, "history");
    }


    public BankAccount addBalance(double... amounts) {
        for (double amount : amounts) {
            balance += amount;
        }
        return this;
    }


    public BankAccount(String accountNumber) {
        this(accountNumber, 0.0);
    }

    public BankAccount(BankAccount other) {
        this(other.accountNumber, other.balance);
    }

    // BankAccount.getAccountsCreated()
    public static int getAccountsCreated() {
        return accountsCreated;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAccount other = (BankAccount) o;
        return Objects.equals(accountNumber, other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }


    @Override
    public String toString() {
        return "BankAccount[accountNumber=" + accountNumber;
    }

}
