package com.solvd.airport;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.DirectionRepository;
import com.solvd.airport.service.DirectionService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Config config = new Config();
        config.getFromFile("config.properties");
        String driver = Config.getDriver();
        String url = Config.getUrl();
        String username = Config.getUsername();
        String password = Config.getPassword();
        Integer poolsize = Config.getPoolsize();
        System.out.println(driver + "\n" + url + "\n" + username + "\n" + password + "\n" + poolsize);

        try { //drivermanager class который помогает создавать получать конекшены к бд
            Class.forName(driver); // вызовется статический блок и зарегистрируется драйвер
//            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        Connection connection;
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        try {
////            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/airport", "novash", "12345");
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        result = Insert.insert("insert into aircarriers(name) values ('NewAidjjdrddddff')");
//        System.out.println("insert result " + result);

        System.out.println("\nSelectAircarrier.select()");
        SelectAircarrier.select();

        System.out.println("\nSelectFlight.select()");
        SelectFlight.select();

        int result;
        System.out.println("\nupdate directions set country");
        result = Update.update("update directions set country = 'dddd' where id = 1;");
        System.out.println("update result " + result);

        Aircarrier aircarrier = new Aircarrier();
        aircarrier.setName("NewAir222");

        Flight flight = new Flight();
        flight.setDate(LocalDate.now());
        flight.setNumber(111);

        Direction direction = new Direction();
        direction.setCountry("Canada");
//        direction.setDistance(999);
        DirectionService directionService;
        DirectionRepository directionRepository;
    }
}