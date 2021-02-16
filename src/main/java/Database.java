import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;
    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public Database(String USER, String PASSWORD, String URL) {
        this.connection = connection;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.URL = URL;

        try {
            Class.forName("com.mysql.cj.jbdc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error! Can't instantiate SQL Driver class");
        }
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error! Can't establish connection to SQL database");
        }
        return connection;
    }
}
