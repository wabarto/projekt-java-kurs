final public class BusinessAccount extends BankAccount {

    private String companyName;

    public BusinessAccount(String accountNumber, double balance, String companyName){
        super(accountNumber, balance);
        this.companyName = companyName;
    }

    @Override
    public String getAccountType() {
        return "firmowe - " + companyName;
    }

    @Override
    public double getMonthlyCost() {
        return 49.99;
    }
}
