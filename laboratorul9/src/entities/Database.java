package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/movies?useSSL=false&serverTimezone=" + TimeZone.getDefault().getID();
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "dba";
    private static final String PASSWORD = "sql";
    private static Connection connection = null;

    private Database() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    public static void createConnection() throws SQLException {
        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        connection= DriverManager.getConnection(url,USER,PASSWORD);
        connection.setAutoCommit(false);
    }

    public static void closeConnection() throws SQLException{
        try{connection.close(); }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void commit() throws SQLException{
        connection.commit();
    }

    public static void rollback() throws SQLException{
        connection.rollback();
    }
}