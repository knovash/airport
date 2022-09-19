package com.solvd.airport;

import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.ConnectionPool;

import java.sql.Connection;
import java.sql.*;

public class Insert {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static int insert(String sql) {
        int result;
//        Connection connection;
//        try {
//            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        Connection connection = CONNECTION_POOL.getConnection();

        try { // INSERT
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
