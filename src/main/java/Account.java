public class Account {

    private int account_id;
    private double currentAmount;

    public Account(int account_id, int currentAmount) {
        this.account_id = account_id;
        this.currentAmount = currentAmount;
    }

    public void updateAccountAmount(){
        // TODO: Make the code.
    }

    public int getAccount_id() {
        return account_id;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
