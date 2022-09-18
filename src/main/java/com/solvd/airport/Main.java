package com.solvd.airport;

import com.solvd.airport.persistance.Settings;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Settings settings = new Settings();
        settings.getFromFile("config.properties");
        System.out.println(settings);
        String driver = settings.getDriver();
        String url = settings.getUrl();
        String username = settings.getUsername();
        String password = settings.getPassword();
        String poolsize = settings.getPoolsize();
        System.out.println(driver + "\n" + url + "\n" + username + "\n" + password + "\n" + poolsize);


        Connection connection;
        //drivermanager class который помогает создавать получать конекшены к бд
        try {
            Class.forName(driver);
//            Class.forName("com.mysql.cj.jdbc.Driver"); // вызовется статический блок и зарегистрируется драйвер
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/airport", "novash", "12345");
            connection = DriverManager.getConnection(url, username, password);
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