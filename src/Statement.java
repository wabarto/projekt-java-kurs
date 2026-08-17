record Statement(String accountNumber, String accountType, double balance) {

    Statement {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Account number is required");
        }
        accountNumber = accountNumber.toUpperCase();
    }

    public String formatted() {
        return "Account number: " + accountNumber;
    }

    public static Statement from(BankAccount account) {
        return new Statement(account.getAccountNumber(), account.getAccountType(), account.getBalance());
    }

}
