import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
  
    public static Connection getConnection() throws SQLException {
        

        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
            String url = "jdbc:mysql://localhost:3306/my_db";
            String username = "root";
            String password = "GTAvicecity123!";
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
