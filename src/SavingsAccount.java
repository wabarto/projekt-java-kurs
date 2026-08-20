import java.math.BigDecimal;

@Audited("HIGH")
final public class SavingsAccount extends BankAccount {
    private final BigDecimal interestRate;


    public SavingsAccount(String accountNumber, BigDecimal balance, BigDecimal interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Audited(value = "CRITICAL", logAmount = true)
    public void addInterest() {
        deposit(balance.multiply(interestRate));
    }

    @Override
    public String getAccountType() {
        return "oszczędnościowe";
    }

    @Override
    public BigDecimal getMonthlyCost() {
        return BigDecimal.valueOf(0);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + (interestRate.multiply(BigDecimal.valueOf(100)));
    }

//    @Override cannot override final
//    public String formatBalance() {
//        return super.getDescription() + (interestRate * 100);
//    }
}
