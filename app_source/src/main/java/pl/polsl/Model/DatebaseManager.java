package pl.polsl.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatebaseManager {
    private static final String URL = "jdbc:derby://localhost:1527/sudoku;create=true";
    private static final String USER = ""; 
    private static final String PASSWORD = ""; 
    private static Connection connection = null;

    public static void connect() {
        try {
            // load driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            if (USER.isEmpty() && PASSWORD.isEmpty()) {
                // Połączenie bez uwierzytelniania
                connection = DriverManager.getConnection(URL);
            } else {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            System.out.println("DB is connect");

        } catch (ClassNotFoundException e) {
            System.err.println("DBC drivers lack");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("DB connection error");
            e.printStackTrace();
        }
    }

    // download connection
    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Połączenie z bazą danych zamknięte.");
            } catch (SQLException e) {
                System.err.println("Błąd przy zamykaniu połączenia.");
                e.printStackTrace();
            }
        }
    }
    
}
