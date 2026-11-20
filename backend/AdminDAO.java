
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public String getPasswordHash(String username) throws SQLException {

        String sql = "SELECT password_hash FROM admin WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getString("password_hash");
                }
            }
        }

        return null;
    }

    public boolean usernameExists(String username) throws SQLException {

        String sql = "SELECT admin_id FROM admin WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public void createAdmin(String username, String passwordHash,
                            String fullName, String email) throws SQLException {

        String sql = "INSERT INTO admin "
                + "(username, password_hash, full_name, email) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setString(3, fullName);
            statement.setString(4, email);

            statement.executeUpdate();
        }
    }
}
