import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBController {

    private Database database;

    public DBController(Database database) {
        this.database = database;
    }

    public createCustomer() throws SQLException {
        String sql = "select * from bank.customers";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int customer_id = rs.getInt("Customer_ID");
                String
            }
        }

    }

    public depositFromAccount(Account account, double amount){
        String sql = "SELECT * FROM bank.account";

    }

    public withdrawFromAccount(Account account, double amount){

    }

    public showAllTransactions(Account account){
        /// TODO: 16-02-2021 vis alle transaktioner der er på den pågældende konto
    }

    public showAccountAmount(Account account){
        /// TODO: 16-02-2021 hent nuværende amount som ligger på databasen, og print
    }

    public updateAccount(Account account){

    }


}
