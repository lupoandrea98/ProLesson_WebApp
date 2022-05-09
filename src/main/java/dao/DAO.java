package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private final String url1 = "jdbc:mysql://localhost:3306/project";
    private final String user = "root";
    private final String password = "";

    public String getUrl1() {
        return url1;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("ERROR -> " + e.getMessage());
        }
    }


}
