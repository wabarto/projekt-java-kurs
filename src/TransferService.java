import java.math.BigDecimal;

public class TransferService {
    private final Notifier notifier;

    public TransferService(Notifier notifier) {
        if (notifier == null) {
            throw new IllegalArgumentException();
        }

        this.notifier = notifier;
    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        from.withdraw(amount);
        to.deposit(amount);
        notifier.send("Wyslano przelew");
    }
}
