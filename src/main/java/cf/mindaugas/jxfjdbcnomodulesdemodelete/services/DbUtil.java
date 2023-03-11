package cf.mindaugas.jxfjdbcnomodulesdemodelete.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/jxfjdbcnomodulesdemodelete";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public DbUtil() {
        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.print("Database connection was not established ");
            System.err.println("check if db is working or credentials are correct");
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
