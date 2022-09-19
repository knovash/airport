package com.solvd.airport;

import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.ConnectionPool;

import java.sql.*;
import java.sql.Connection;

public class SelectAircarrier {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static void select() {
        Connection connection = CONNECTION_POOL.getConnection();

        try { // SELECT
            PreparedStatement statement = connection.prepareStatement("select id, name from aircarriers order by id;", Statement.RETURN_GENERATED_KEYS);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
