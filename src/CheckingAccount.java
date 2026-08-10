public class CheckingAccount extends BankAccount {

    private double monthlyFee;

    public CheckingAccount(String accountNumber, double balance, double monthlyFee){
        super(accountNumber, balance);
        this.monthlyFee = monthlyFee;
    }

    public void chargeMonthlyFee() {
        withdraw(monthlyFee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + (monthlyFee * 100);
    }
}
