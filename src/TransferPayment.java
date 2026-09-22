import java.math.BigDecimal;

public record TransferPayment(BigDecimal amount, String iban) implements Payment {
}
