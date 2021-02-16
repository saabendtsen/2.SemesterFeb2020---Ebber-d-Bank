import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBController {


    private Database database;

    public DBController(Database database) {
        this.database = database;
    }

    public void getCustomersInfo (){
        String sql = "SELECT * FROM bank.customers";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customer_id = rs.getInt("Customer_ID");
                String customer_name = rs.getString("Customer_Name");
                String customer_city = rs.getString("Customer_City");

                System.out.println("Id: "+customer_id+ " Navn: "+customer_name+ " By: "+customer_city+"\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createCustomer() throws SQLException {
        String sql = "select * from bank.customers";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int customer_id = rs.getInt("Customer_ID");
                // TODO: 16-02-2021 mangler
            }
        }
    }

    public void deleteCustomer() {
        /// TODO: 16-02-2021 add
    }

    public void depositFromAccount(Account account, double amount) {
        boolean result = false;
        String sql = "insert into bank.transaction (Transaction_amount) values (?)";
            try (PreparedStatement ps = database.connect().prepareStatement(sql)) {
                ps.setDouble(3, amount);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public void withdrawFromAccount(Account account, double amount){
        /// TODO: 16-02-2021 update from bank.transaction (Transaction_amount)
    }

    public void showAllTransactions(Account account){
        /// TODO: 16-02-2021 vis alle transaktioner der er på den pågældende konto
    }


    public double returnCurrentAccountAmount(int accountID){
        double amount = 0;
        String sql = "SELECT SUM(Transaction_amount) FROM bank.transaction WHERE Account_ID = ?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1,accountID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount = rs.getDouble("SUM(Transaction_amount)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return amount;
    }

    public void updateAccount(Account account){

    }

}
