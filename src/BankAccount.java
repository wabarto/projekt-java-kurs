import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

sealed abstract class BankAccount implements Comparable<BankAccount> permits SavingsAccount, CheckingAccount, BusinessAccount {
    private AccountStatus status = AccountStatus.ACTIVE;

    private static int accountsCreated = 0;

    private static final double MIN_INITIAL_BALANCE = 0.0;

    private final List<String> history = new ArrayList<>();

    protected final String accountNumber;
    protected BigDecimal balance;

    private int transactionCount;

    public BankAccount(String accountNumber, BigDecimal balance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }

        if (balance.signum() < 0) {
            throw new IllegalArgumentException("Balance has to be > 0");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
        history.add("Initial balance: " + balance);
        accountsCreated++;
    }


    public abstract String getAccountType();

    public abstract BigDecimal getMonthlyCost(); // override

    public final String getMonthlyStatement() {
        return String.format("%s [%s] - saldo %.2f, koszt miesieczny %.2f", accountNumber, getAccountType(), balance, getMonthlyCost());
    }

    private BankAccount(String accountNumber, BigDecimal balance, String history) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

//    public static BankAccount createEmpty(String accountNumber, double balance) {
////        return new BankAccount(accountNumber, balance);
//        return new BankAccount(accountNumber, balance, "history");
//    }

    public String getDescription() {
        return String.format("%s: %.2f zł", accountNumber, balance);
    }


    public BankAccount addBalance(BigDecimal... amounts) {
        for (BigDecimal amount : amounts) {
            balance = balance.add(amount);
        }
        return this;
    }


    public BankAccount(String accountNumber) {
        this(accountNumber, BigDecimal.valueOf(0.0));
    }

    public BankAccount(BankAccount other) {
        this(other.accountNumber, other.balance);
    }

    // BankAccount.getAccountsCreated()
    public static int getAccountsCreated() {
        return accountsCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount has to be > 0");
        }

        balance = balance.add(amount);

    }

    public void deposit(BigDecimal amount, String type) { //overload
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount has to be > 0");
        }

        balance = balance.add(amount);

    }

    public List<String> getHistory() {
        return List.copyOf(history);
    }

    public void withdraw(BigDecimal amount) {
        if (!status.canWithdraw()) {
            throw new IllegalStateException();
        }

        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount has to be > 0");
        }

        if (amount.compareTo(balance) > 0) {
            throw new IllegalStateException("Incorrect amount");
        }

        balance = balance.subtract(amount);

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



    public final String formatBalance() {
        return String.format("%,.2f", balance);
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public int compareTo(BankAccount other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }
}
