public class TransaktionHandler {

    Database db = new Database(Main.DBUSER,Main.DBPASS,Main.DBURL);
    DBController dbc = new DBController(db);



    public void deposit(int account_id, double amount){

        if (amount > 0) {
            dbc.transferFromToAccount(account_id, amount);
            System.out.println("Du har nu deposit: " + amount + "kr" + " på konto nummer: " + account_id + "\n");
        }
    }

    public void withdraw(int account_id, double amount){

        if(dbc.returnCurrentAccountAmount(account_id) > amount){
        dbc.transferFromToAccount(account_id,-amount);
            System.out.println("Du har nu hævet: " + amount + "kr" + " fra konto nummer: " + account_id);
        } else {
            System.out.println("Der er ikke nok penge på din konto. Dit fattige svin");
        }
    }

    public void transferBetweenAccount(int fromAccount, int toAccount, double amount){
            withdraw(fromAccount, amount);
            deposit(toAccount, amount);
    }

}
