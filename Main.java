public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(
                "C01", "Gotlhe", "Sekhomba", "Kanye", "+26776313261", "gotlhe@example.com"
        );

        customer.openSavingsAccount("SAV-01", 2000.00, "Kanye");
        customer.openInvestmentAccount("INV-01", 500.00, "Kanye");
        customer.openChequeAccount("CHQ-01", 1000.00, "Kanye", "BAC", "Gaborone");

        customer.depositToAccount("CHQ-01", 400.00);
        customer.withdrawFromAccount("CHQ-01", 250.00);
        customer.withdrawFromAccount("INV-01", 100.00);

        try {
            customer.withdrawFromAccount("SAV-01", 50.00);
        } catch (UnsupportedOperationException e) {
            System.out.println("Notice: " + e.getMessage());
        }

        for (Account acc : customer.getAccounts()) {
            acc.applyMonthlyInterest();
            System.out.printf("%n%s (%s) -> Balance: BWP %.2f%n",
                    acc.getAccountNumber(), acc.getAccountType(), acc.getBalance());
            for (Transaction tx : acc.getTransactionHistory()) {
                System.out.println("  " + tx.getDetails());
            }
        }
    }
}