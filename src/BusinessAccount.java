import java.math.BigDecimal;

final public class BusinessAccount extends BankAccount {

    private String companyName;

    public BusinessAccount(String accountNumber, BigDecimal balance, String companyName){
        super(accountNumber, balance);
        this.companyName = companyName;
    }

    @Override
    public String getAccountType() {
        return "firmowe - " + companyName;
    }

    @Override
    public BigDecimal getMonthlyCost() {
        return BigDecimal.valueOf(49.99);
    }
}
