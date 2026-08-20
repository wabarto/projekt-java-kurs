import java.math.BigDecimal;

final public class CheckingAccount extends BankAccount {

    private BigDecimal monthlyFee;

    public CheckingAccount(String accountNumber, BigDecimal balance, BigDecimal monthlyFee){
        super(accountNumber, balance);
        this.monthlyFee = monthlyFee;
    }

    public void chargeMonthlyFee() {
        withdraw(monthlyFee);
    }

    @Override
    public String getAccountType() {
        return "bieżące";
    }

    @Override
    public BigDecimal getMonthlyCost() {
        return monthlyFee;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + (monthlyFee.multiply(BigDecimal.valueOf(100)));
    }
}
