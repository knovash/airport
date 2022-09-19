package com.solvd.airport;

import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.ConnectionPool;

import java.sql.Connection;
import java.sql.*;

public class SelectFlight {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static void select() {
//        Connection connection;
//        try {
//            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        Connection connection = CONNECTION_POOL.getConnection();

        try { // SELECT
            PreparedStatement statement = connection.prepareStatement(
                    "select id as flight_id, number as flight_name, date as flight_date from flights;", Statement.RETURN_GENERATED_KEYS);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                long number = resultSet.getLong(2);
                Date date = resultSet.getDate(3);
                System.out.println(id + " " + number + " " + date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
