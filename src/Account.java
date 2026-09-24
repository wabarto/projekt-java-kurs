import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Setter
@Getter
public class Account {

    private final String id;
    private double balance;

    private final Lock lock = new ReentrantLock();

    Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    void withdraw(double amount) {
        balance -= amount;
    }

    void deposit(double amount) {
        balance += amount;
    }

}
