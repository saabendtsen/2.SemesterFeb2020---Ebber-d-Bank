public class TransaktionHandler {

    DBController db = new DBController();



    public void deposit(Account account, double amount){


        Transaction t = new Transaction(account.getAccount_id(), amount);


        account.updateAccountAmount();

    }

    public void withdraw(Account account, double amount){
        account.updateAccountAmount();

        if(account.getCurrentAmount() > amount){


        } else {
            System.out.println("Der er ikke nok penge på din konto. Dit fattige svin");
        }


    }

    public void transferBetweenAccount(Account fromAccount, Account toAccount, double Amount){

    }

    public void showAllTransaktions(Account account){

    }
}
