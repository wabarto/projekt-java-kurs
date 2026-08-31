import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InsufficientFundsException extends BankException {
    private final BigDecimal requested;
    private final BigDecimal available;

    InsufficientFundsException(BigDecimal requested, BigDecimal available) {
        super("Brak srodkow, zazadano %2.f, dostepne %2.f".formatted(requested, available), "INSUFFICIENT_FUNDS");
        this.requested = requested;
        this.available = available;
    }
}
