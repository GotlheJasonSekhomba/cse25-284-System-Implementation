import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private final String customerId;
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String firstName, String surname,
                    String address, String phoneNumber, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public SavingsAccount openSavingsAccount(String accountNumber, double initialDeposit, String branch) {
        SavingsAccount acc = new SavingsAccount(accountNumber, initialDeposit, branch, this);
        accounts.add(acc);
        return acc;
    }

    public InvestmentAccount openInvestmentAccount(String accountNumber, double initialDeposit, String branch) {
        InvestmentAccount acc = new InvestmentAccount(accountNumber, initialDeposit, branch, this);
        accounts.add(acc);
        return acc;
    }

    public ChequeAccount openChequeAccount(String accountNumber, double initialDeposit,
                                           String branch, String employerName, String employerAddress) {
        ChequeAccount acc = new ChequeAccount(accountNumber, initialDeposit, branch, this, employerName, employerAddress);
        accounts.add(acc);
        return acc;
    }

    public void depositToAccount(String accountNumber, double amount) {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        findAccount(accountNumber).withdraw(amount);
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        throw new IllegalArgumentException("Account not found: " + accountNumber);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getFullName() { return firstName + " " + surname; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
}