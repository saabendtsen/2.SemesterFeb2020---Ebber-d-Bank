import java.time.LocalDateTime;

public class Transaction {

    private String account_id;
    private double amount;
//    private LocalDateTime ldt;

    public Transaction(String account_id, int amount) {
//        this.ldt = LocalDateTime.now();
        this.account_id = account_id;
        this.amount = amount;
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
