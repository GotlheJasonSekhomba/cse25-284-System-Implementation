import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final String description;
    private final String reference;

    public Transaction(String transactionId, LocalDateTime dateTime, String type,
                       double amount, String description, String reference) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.reference = reference;
    }

    public String getDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s | %s | %s | BWP %.2f | %s | %s",
                transactionId, dateTime.format(fmt), type, amount, reference, description);
    }

    public String getTransactionId() { return transactionId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getReference() { return reference; }
}