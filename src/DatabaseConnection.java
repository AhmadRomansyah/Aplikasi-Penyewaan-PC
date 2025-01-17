import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/penyewaan_pc";
            String user = "root";  // Sesuaikan dengan username MySQL Anda
            String password = "";   // Sesuaikan dengan password MySQL Anda
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return conn;
    }
}


