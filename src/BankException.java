import lombok.Getter;

@Getter
public class BankException extends RuntimeException {
    private final String errorCode;

    BankException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    BankException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
