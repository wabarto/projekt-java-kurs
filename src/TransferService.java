import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@RequiredArgsConstructor
//@Slf4j
public class TransferService {
    private final Notifier notifier;

//    public TransferService(Notifier notifier) {
//        if (notifier == null) {
//            throw new IllegalArgumentException();
//        }
//
//        this.notifier = notifier;
//    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        from.withdraw(amount);
        to.deposit(amount);
        notifier.send("Wyslano przelew");
    }
}
