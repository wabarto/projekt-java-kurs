public class Account {

    private final String id;
    private double balance;

    Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    double getBalance() {
        return balance;
    }

    String getId() {
        return id;
    }

    void withdraw(double amount) {
        balance -= amount;
    }

    void deposit(double amount) {
        balance += amount;
    }

}
