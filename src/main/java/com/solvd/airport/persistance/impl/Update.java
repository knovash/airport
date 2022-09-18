package com.solvd.airport.persistance.impl;

import com.solvd.airport.persistance.Config;

import java.sql.Connection;
import java.sql.*;

public class Update {

    public static int update(String sql) {
        Connection connection;
        int result;

        try {
            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try { // UPDATE
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
