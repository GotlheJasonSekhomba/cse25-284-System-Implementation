import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected AccountType accountType;
    protected double balance;
    protected String branch;
    protected String status;
    protected LocalDate openDate;
    protected Customer owner;
    protected List<Transaction> transactions = new ArrayList<>();
    private int txCounter = 1;

    public Account(String accountNumber, AccountType accountType, double initialBalance,
                   String branch, Customer owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Account must belong to a customer.");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber.trim();
        this.accountType = accountType;
        this.balance = round2(initialBalance);
        this.branch = (branch == null || branch.isBlank()) ? "Main Branch" : branch.trim();
        this.status = "ACTIVE";
        this.openDate = LocalDate.now();
        this.owner = owner;

        if (this.balance > 0) {
            addTransaction("OPENING_DEPOSIT", this.balance, "Initial deposit");
        }
    }

    public void deposit(double amount) {
        checkActive();
        checkPositive(amount, "Deposit");
        double rounded = round2(amount);
        balance = round2(balance + rounded);
        addTransaction("DEPOSIT", rounded, "Deposit");
    }

    public abstract void withdraw(double amount);

    public abstract void applyMonthlyInterest();

    protected void checkActive() {
        if (!"ACTIVE".equalsIgnoreCase(status)) {
            throw new IllegalStateException("Account is not active: " + status);
        }
    }

    protected void checkPositive(double amount, String action) {
        if (amount <= 0) {
            throw new IllegalArgumentException(action + " amount must be greater than zero.");
        }
    }

    protected void checkSufficientFunds(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
    }

    protected void addTransaction(String type, double amount, String description) {
        String id = "TX" + (txCounter++);
        String ref = "REF" + System.currentTimeMillis() + "-" + id;
        transactions.add(new Transaction(id, LocalDateTime.now(), type, round2(amount), description, ref));
    }

    protected double round2(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public AccountType getAccountType() { return accountType; }
    public String getBranch() { return branch; }
    public String getStatus() { return status; }
    public LocalDate getOpenDate() { return openDate; }
    public Customer getOwner() { return owner; }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }
}