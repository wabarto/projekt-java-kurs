import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TransferRequest {
    private String fromAccount = "10";
    private String toAccount;
}
