package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospitalmanagementsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";

    private static Connection connection = null;

    public static Connection getDBConn() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            throw new RuntimeException("Database connection error", e);
        }
        return connection;
    }
}
