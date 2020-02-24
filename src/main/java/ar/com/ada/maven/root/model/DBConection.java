package ar.com.ada.maven.root.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static Connection connection;
    private static String user = "adatp";
    private static String host = "jdbc:mysql://localhost:3306/";
    private static String password = "adatp";
    private static String db = "adabank";
    private static String drive = "com.mysql.cj.jdbc.Driver";

    public DBConection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(drive).newInstance();
                String url = host + db + "?serverTimezone=UTC";
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("ERROR CONNECTION");
        }
        return connection;
    }
}
