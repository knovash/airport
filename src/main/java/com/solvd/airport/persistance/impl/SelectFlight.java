package com.solvd.airport.persistance.impl;

import com.solvd.airport.persistance.Config;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;

public class SelectFlight {

    public static void select() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
