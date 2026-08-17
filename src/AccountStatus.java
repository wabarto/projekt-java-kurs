public enum AccountStatus {
    ACTIVE("Aktywne", true, true),
    BLOCKED("Zablokowane", true, false),
    CLOSED("Zamkniete", false, false);

    private final String label;
    private boolean canDeposit;
    private boolean canWithdraw;

    AccountStatus(String label, boolean canDeposit, boolean canWithdraw) {
        this.label = label;
        this.canWithdraw = canWithdraw;
        this.canDeposit = canDeposit;
    }

    public String getLabel() {
        return label;
    }

    public boolean canDeposit() {
        return canDeposit;
    }

    public boolean canWithdraw() {
        return canWithdraw;
    }

}
