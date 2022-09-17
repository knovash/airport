package com.solvd.airport;

import com.solvd.airport.port.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        //drivermanager class который помогает создавать получать конекшены к бд
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // вызовется статический блок и зарегистрируется драйвер
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/airport", "novash", "12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.prepareStatement("insert into aircarriers(name) values ('NewAir')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        String carrierName = "NewAir2";
//        try {
//            PreparedStatement statement = connection.prepareStatement("insert into aircarriers(name) values (?)", Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, carrierName );
//            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            while (resultSet.next())
//            {
//                long id = resultSet.getLong(1);
//                System.out.println(id);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        try {
            PreparedStatement statement = connection.prepareStatement("select id, name from aircarriers where id=?;", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, 2 );
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n");

        try {
            PreparedStatement statement = connection.prepareStatement("select id, name from aircarriers order by id;", Statement.RETURN_GENERATED_KEYS);
//            statement.setLong(1, 2 );
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}