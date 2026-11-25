public class InvestmentAccount extends Account {
    private static final double MIN_INITIAL_DEPOSIT = 500.00;
    private static final double INTEREST_RATE = 0.05; // 5% monthly

    public InvestmentAccount(String accountNumber, double initialDeposit, String branch, Customer owner) {
        super(accountNumber, AccountType.INVESTMENT, checkMinDeposit(initialDeposit), branch, owner);
    }

    private static double checkMinDeposit(double deposit) {
        if (deposit < MIN_INITIAL_DEPOSIT) {
            throw new IllegalArgumentException("Investment Account requires at least BWP 500.00 to open.");
        }
        return deposit;
    }

    @Override
    public void withdraw(double amount) {
        checkActive();
        checkPositive(amount, "Withdrawal");
        double rounded = round2(amount);
        checkSufficientFunds(rounded);
        balance = round2(balance - rounded);
        addTransaction("WITHDRAWAL", rounded, "Investment withdrawal");
    }

    @Override
    public void applyMonthlyInterest() {
        checkActive();
        double interest = round2(balance * INTEREST_RATE);
        if (interest > 0) {
            balance = round2(balance + interest);
            addTransaction("INTEREST", interest, "Monthly investment interest (5%)");
        }
    }
}