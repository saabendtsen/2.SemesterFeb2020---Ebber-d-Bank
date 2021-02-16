import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String account_id;
    private double amount;
    private final LocalDateTime ldt = LocalDateTime.now();
    String formattedTime = ldt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public Transaction(String account_id, int amount) {
        this.account_id = account_id;
        this.amount = amount;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
