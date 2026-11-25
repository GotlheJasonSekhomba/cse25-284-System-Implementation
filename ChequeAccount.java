public class ChequeAccount extends Account {
    private final String employerName;
    private final String employerAddress;

    public ChequeAccount(String accountNumber, double initialDeposit, String branch,
                         Customer owner, String employerName, String employerAddress) {
        super(accountNumber, AccountType.CHEQUE, initialDeposit, branch, owner);
        if (employerName == null || employerName.isBlank() || employerAddress == null || employerAddress.isBlank()) {
            throw new IllegalArgumentException("Employer name and address are required for a Cheque Account.");
        }
        this.employerName = employerName.trim();
        this.employerAddress = employerAddress.trim();
    }

    @Override
    public void withdraw(double amount) {
        checkActive();
        checkPositive(amount, "Withdrawal");
        double rounded = round2(amount);
        checkSufficientFunds(rounded);
        balance = round2(balance - rounded);
        addTransaction("WITHDRAWAL", rounded, "Cheque withdrawal");
    }

    @Override
    public void applyMonthlyInterest() {
        // Cheque accounts do not earn monthly interest
    }

    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }
}