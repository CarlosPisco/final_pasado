package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        String user = "root";
        String pass = "thankyou2022";
        String database = "movies";
        String url = "jdbc:mysql://35.247.67.136:3306/"+database;

        return DriverManager.getConnection(url, user, pass);
    }


}
