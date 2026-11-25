public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.0005; // 0.05% monthly

    public SavingsAccount(String accountNumber, double initialDeposit, String branch, Customer owner) {
        super(accountNumber, AccountType.SAVINGS, initialDeposit, branch, owner);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed on a Savings Account.");
    }

    @Override
    public void applyMonthlyInterest() {
        checkActive();
        double interest = round2(balance * INTEREST_RATE);
        if (interest > 0) {
            balance = round2(balance + interest);
            addTransaction("INTEREST", interest, "Monthly savings interest (0.05%)");
        }
    }
}