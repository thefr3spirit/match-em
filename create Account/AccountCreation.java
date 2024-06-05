import java.sql.*;

public class AccountCreation {
    private Connection connection;

    public AccountCreation() {
        try {
            // Establish connection to MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_app_db", "your_mysql_username", "your_mysql_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new account
    public boolean createAccount(String username, String email, String password) {
        String insertSQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            return true; // Account created successfully
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                return false; // Username or email already exists
            }
            e.printStackTrace();
            return false;
        }
    }

    // Check if an email is already in use
    public boolean emailExists(String email) {
        String querySQL = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(querySQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if a password matches for a given username or email
    public boolean validatePassword(String usernameOrEmail, String password) {
        String querySQL = "SELECT password FROM users WHERE username = ? OR email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(querySQL)) {
            pstmt.setString(1, usernameOrEmail);
            pstmt.setString(2, usernameOrEmail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update password for a given username or email
    public boolean updatePassword(String usernameOrEmail, String newPassword) {
        String updateSQL = "UPDATE users SET password = ? WHERE username = ? OR email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, usernameOrEmail);
            pstmt.setString(3, usernameOrEmail);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
