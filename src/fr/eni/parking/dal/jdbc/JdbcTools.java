package fr.eni.parking.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    public static Connection getConnection() throws SQLException {
        String urldb = "jdbc:mysql://localhost:3306/PARKING?autoReconnect=true&useSSL=false&characterEncoding=latin1&useConfigs=maxPerformance";
        return DriverManager.getConnection(urldb, "root", "");

    }
}

