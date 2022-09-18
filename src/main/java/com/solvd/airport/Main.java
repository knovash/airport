package com.solvd.airport;

import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.impl.Insert;
import com.solvd.airport.persistance.impl.SelectAircarrier;
import com.solvd.airport.persistance.impl.SelectFlight;
import com.solvd.airport.persistance.impl.Update;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Config config = new Config();
        config.getFromFile("config.properties");
        String driver = Config.getDriver();
        String url = Config.getUrl();
        String username = Config.getUsername();
        String password = Config.getPassword();
        String poolsize = Config.getPoolsize();
        System.out.println(driver + "\n" + url + "\n" + username + "\n" + password + "\n" + poolsize);

        Connection connection;

        try { //drivermanager class который помогает создавать получать конекшены к бд
            Class.forName(driver); // вызовется статический блок и зарегистрируется драйвер
//            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/airport", "novash", "12345");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Insert.insert("insert into aircarriers(name) values ('NewAirdddff')");


        System.out.println("\nSelectAircarrier.select()");
        SelectAircarrier.select();

        System.out.println("\nSelectFlight.select()");
        SelectFlight.select();

        System.out.println("\nupdate directions set country");
        int res = Update.update("update directions set country = 'dddd' where id = 1;");
        System.out.println("update result " + res);

    }
}