@Audited("HIGH")
final public class SavingsAccount extends BankAccount {
    private final double interestRate;


    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Audited(value = "CRITICAL", logAmount = true)
    public void addInterest() {
        deposit(balance * interestRate);
    }

    @Override
    public String getAccountType() {
        return "oszczędnościowe";
    }

    @Override
    public double getMonthlyCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + (interestRate * 100);
    }

//    @Override cannot override final
//    public String formatBalance() {
//        return super.getDescription() + (interestRate * 100);
//    }
}
