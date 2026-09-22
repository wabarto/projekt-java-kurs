import java.math.BigDecimal;

public record CardPayment(BigDecimal amount, String cardNumber) implements Payment {
}
