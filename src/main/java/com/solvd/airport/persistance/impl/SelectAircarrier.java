package com.solvd.airport.persistance.impl;

import com.solvd.airport.persistance.Config;

import java.sql.*;
import java.sql.Connection;

public class SelectAircarrier {

    public static void select() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
