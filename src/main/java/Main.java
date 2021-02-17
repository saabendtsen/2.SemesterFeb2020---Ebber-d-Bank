public class Main {
    // Database info
    static final String DBURL = "jdbc:mysql://localhost:3306/bank?serverTimezone=UTC";
    static final String DBUSER = "root";
    static final String DBPASS = "1234";

    public static void main(String[] args) {
        Database d = new Database(DBUSER,DBPASS,DBURL);
        DBController b = new DBController(d);

        System.out.println(b.getAccountDetails(1));


        Menu m = new Menu();

        m.mainMenu();
    }


    // Deposit på account
        /*
        boolean result = DBController.depositFromAccount(account,amount);
            if (result) {
                System.out.println("Indsat "+amount+" på konto "+ account);
            } else {
                System.out.println(account+" Findes ikke!");
            }
         */

    // Delete account
        /*
        boolean result = DBController.deleteCustomer(accountID);
            if (result) {
                System.out.println("konto:  "+accountID+" er nu slettet");
            } else {
                System.out.println(accountID+" Findes ikke!");
            }
         */

    // insert new customer
        /*
        public void adduser(){
            System.out.println("---Indsæt ny kunde---");
            System.out.println("indtast kunder navn");
            String customer_name = sc.nextLine();
            System.out.println("indtast kundens by");
            String customer_city = sc.nextLine();
            Customer newCustomer = new Customer(0,customer_name,customer_city);
            boolean result = dbc.createCustomer(newCustomer);
            if (result){
                System.out.println("Kunde med nummer "+" er nu blevet tilføjet til DB");
            } else {
                System.out.println("Kunde kunne ikke tilføjes! Kunde ID "+" bruges allerede!");
            }
        }
         */

    // see all transaktions on account id
    /*
    System.out.println(showAllTransactions(account id));
     */

}
