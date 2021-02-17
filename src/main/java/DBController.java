import java.sql.*;

public class DBController {
    private final Database database;

    public DBController(Database database) {
        this.database = database;
    }

    public void getCustomersInfo (){
        StringBuilder text = new StringBuilder();
        String sql = "SELECT customers.Customer_ID, account.Account_ID, customers.Customer_Name, customers.Customer_City\n" +
                "FROM customers\n" +
                "INNER JOIN account ON account.Customer_ID=customers.Customer_ID";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt("Account_ID");
                int customer_id = rs.getInt("Customer_ID");
                String customer_name = rs.getString("Customer_Name");
                String customer_city = rs.getString("Customer_City");
                text.append("Kunde Id: ").append(customer_id).append(" - Konto Id: ").append(account_id).append(" - Navn: ").append(customer_name).append(" - By: ").append(customer_city).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(text);
    }

    public boolean getCustomerAccountDetails(int customerID){
        boolean result = false;
        StringBuilder text = new StringBuilder();
        String sql = "SELECT customers.Customer_ID, account.Account_ID, customers.Customer_Name\n" +
                "FROM customers\n" +
                "INNER JOIN account ON account.Customer_ID=customers.Customer_ID where customers.Customer_ID=?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1,customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                int accountId = rs.getInt("Account_ID");
                String customerName = rs.getString("Customer_Name");
                text.append("Konto nr: ").append(accountId).append(" - Kunde nr: ").append(customerId).append(" - Kunde navn: ").append(customerName).append(" - Saldo: ").append(returnCurrentAccountAmount(accountId)).append("\n");
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(text);
        return result;
    }

    public boolean getAccountDetails(int accountID){
        boolean result = false;
        String sql = "SELECT * FROM bank.account where Customer_ID=?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1,accountID);
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public int createCustomer(String name, String city) {
        int customerId = 0;
        String sql = "insert into bank.customers (Customer_Name,Customer_City) values (?,?)";
        try (PreparedStatement ps = database.connect().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,name);
            ps.setString(2,city);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    customerId = rs.getInt(1);
                    createAccount(customerId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerId;
    }

    public boolean createAccount(int customerID){
        boolean result = false;
        String sql = "insert into bank.account (Account_ID, Customer_ID) values (?,?)";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1, 0);
            ps.setInt(2,customerID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean deleteCustomer(int customerID) {
        boolean result = false;
        String sql = "delete from bank.customers where Customer_ID = ?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1, customerID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
                deleteAllAccounts(customerID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean deleteAllAccounts(int customerID) {
        boolean result = false;
        String sql = "delete from bank.account where Customer_ID = ?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1, customerID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean transferFromToAccount(int account_id, double amount) {
        boolean result = false;
        String sql = "insert into bank.transaction (Transaction_ID, Transaction_amount, Transaction_date, Account_ID) values (?,?,now(),?)";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)) {
            ps.setInt(1, 0);
            ps.setDouble(2, amount);
            ps.setInt(3, account_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public String showAllTransactions(int accountID){
        StringBuilder result = new StringBuilder();
        String sql = "SELECT transaction.Transaction_ID , transaction.Transaction_amount, transaction.Transaction_date \n" +
                "FROM transaction\n" +
                "INNER JOIN account ON transaction.Account_ID=account.Account_ID where transaction.Account_ID=?";
        try (PreparedStatement ps = database.connect().prepareStatement(sql)){
            ps.setInt(1,accountID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int transactionId = rs.getInt("Transaction_ID");
                double transactionAmount = rs.getDouble("Transaction_amount");
                Date transactionDate = rs.getDate("Transaction_date");
                Time transactionTime = rs.getTime("Transaction_date");
                result.append("Transaktion nr: ").append(transactionId).append(" Beløb : ").append(transactionAmount).append(" @ ").append(transactionDate).append(" ").append(transactionTime).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result.toString();
    }


    public double returnCurrentAccountAmount(int accountID){
        double amount = 0;
        String sql = "SELECT SUM(Transaction_amount) FROM transaction WHERE Account_ID = ?";
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

}
