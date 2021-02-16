public class Account {

    private String account_id;
    private int currentAmount;

    public Account(String account_id, int currentAmount) {
        this.account_id = account_id;
        this.currentAmount = currentAmount;
    }

    public void updateAccountAmount(){
        // TODO: Make the code.

    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }
}
