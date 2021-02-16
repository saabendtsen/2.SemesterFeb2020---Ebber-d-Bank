import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBController {

    private Database database;

    public DBController(Database database) {
        this.database = database;
    }

    public void getCustomerIdInfo (){
        String sql = "SELECT * FROM bank.customers";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customer_id = rs.getInt("Customer_ID");
                String customer_name = rs.getString("Customer_Name");
                String customer_city = rs.getString("Customer_City");
                /// TODO: 16-02-2021 do somthing with this info 
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public createCustomer() throws SQLException {
        String sql = "select * from bank.customers";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int customer_id = rs.getInt("Customer_ID");

            }
        }

    }

    public void depositFromAccount(Account account, double amount){
        String sql = "SELECT * FROM bank.account";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String id = account.getAccount_id();
                id = (rs.getString("Account_ID"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void withdrawFromAccount(Account account, double amount){

    }

    public void showAllTransactions(Account account){
        /// TODO: 16-02-2021 vis alle transaktioner der er på den pågældende konto
    }

    public void showAccountAmount(Account account){
        /// TODO: 16-02-2021 hent nuværende amount som ligger på databasen, og print
    }

    public void updateAccount(Account account){

    }

    public double returnCurrentAccountAmount(){
        return 0;
    }


}
