public class TransaktionHandler {

    Database db = new Database(Main.DBUSER,Main.DBPASS,Main.DBURL);
    DBController dbc = new DBController(db);



    public void deposit(Account account, double amount){
        if (amount > 0) {
            dbc.depositFromAccount(account, amount);
        }
    }

    public void withdraw(Account account, double amount){

        if(account.getCurrentAmount() > amount){
        dbc.withdrawFromAccount(account,amount);
        } else {
            System.out.println("Der er ikke nok penge på din konto. Dit fattige svin");
        }
    }

    public void transferBetweenAccount(Account fromAccount, Account toAccount, double amount){
        if(fromAccount.getCurrentAmount() > amount) {
            withdraw(fromAccount, amount);
            deposit(toAccount, amount);
        }
    }

    public void showAllTransactions(Account account){
        dbc.showAllTransactions(account.getAccount_id());
    }
}
