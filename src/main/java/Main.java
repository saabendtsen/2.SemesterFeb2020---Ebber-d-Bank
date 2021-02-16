public class Main {
    static final String DBURL = "jdbc:mysql://localhost:3306/bank?serverTimezone=UTC";
    static final String DBUSER = "root";
    static final String DBPASS = "1234";

    public static void main(String[] args) {


        Menu m = new Menu();

        m.mainMenu();
    }
}
