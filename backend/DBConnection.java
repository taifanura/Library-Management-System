
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/advanced_library_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Dhaka";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    public static void main(String[] args) {

        try (Connection connection = getConnection()) {

            if (connection != null) {
                System.out.println("Database Connected Successfully!");
            }

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            System.out.println(e.getMessage());

        }
    }
}
