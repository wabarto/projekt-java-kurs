import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Data
// @EqualsAndHashCode(callSuper = true) - przy data zeby na klasie dziedziczacej nie ignorowac pol nadklasy w equals i hashcode
public class Customer {
    private String name;
    private String email;
    private TransactionHistory transactionHistory;
    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }


}
