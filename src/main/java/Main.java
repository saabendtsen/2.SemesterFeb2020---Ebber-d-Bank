public class Main {
    static final String DBURL = "jdbc:mysql://localhost:3306/bank?serverTimezone=UTC";
    static final String DBUSER = "root";
    static final String DBPASS = "1234";

    public static void main(String[] args) {



        Database database = new Database(DBUSER,DBPASS,DBURL);
        DBController d = new DBController(database);

        d.getCustomerIdInfo();

        System.out.println("Sum af transaktioner på konto: 1 = "+d.returnCurrentAccountAmount(2)+"\n");


        Menu m = new Menu();

        m.mainMenu();
    }
}
