import java.time.LocalDateTime;

public class Transaction {

    private String account_id;
    private int amount;
    LocalDateTime ldt;

    public Transaction(String account_id, int amount, LocalDateTime ldt) {
        this.account_id = account_id;
        this.amount = amount;
        this.ldt = ldt;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }
}
