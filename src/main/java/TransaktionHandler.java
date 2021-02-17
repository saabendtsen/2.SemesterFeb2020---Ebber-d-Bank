public class TransaktionHandler {

    Database db = new Database(Main.DBUSER,Main.DBPASS,Main.DBURL);
    DBController dbc = new DBController(db);



    public void deposit(int account_id, double amount){
        if (amount > 0) {
            dbc.depositFromAccount(account_id, amount);
        }
    }

    public void withdraw(int account_id, double amount){

        if(dbc.returnCurrentAccountAmount(account_id) > amount){
        dbc.withdrawFromAccount(account_id,amount);
        } else {
            System.out.println("Der er ikke nok penge på din konto. Dit fattige svin");
        }
    }

    public void transferBetweenAccount(Account fromAccount, Account toAccount, double amount){
        if(fromAccount.getCurrentAmount() > amount) {
            withdraw(fromAccount.getAccount_id(), amount);
            deposit(toAccount.getAccount_id(), amount);
        }
    }

    public void showAllTransactions(Account account){
        dbc.showAllTransactions(account.getAccount_id());
    }
}
