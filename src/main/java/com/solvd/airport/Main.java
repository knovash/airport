package com.solvd.airport;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.DirectionRepository;
import com.solvd.airport.persistance.impl.DirectionRepositoryImpl;
import com.solvd.airport.persistance.impl.PassengerRepositoryImpl;
import com.solvd.airport.persistance.impl.PassportRepositoryImpl;
import com.solvd.airport.service.DirectionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

//        System.out.println("\nSelectAircarrier.select()");
//        SelectAircarrier.select();
//
//        System.out.println("\nSelectFlight.select()");
//        SelectFlight.select();
//
//        int result;
//        System.out.println("\nupdate directions set country");
//        result = Update.update("update directions set country = 'dddd' where id = 1;");
//        System.out.println("update result " + result);

//        Aircarrier aircarrier = new Aircarrier();
//        aircarrier.setName("NewAir222");

//        Flight flight = new Flight();
//        flight.setDate(LocalDate.now());
//        flight.setNumber(111);

        // CREATE
//        Direction direction = new Direction();
//        direction.setCountry("Canadddda");
//        direction.setDistance(BigDecimal.valueOf(999));
//        DirectionRepositoryImpl directionRepository = new DirectionRepositoryImpl();
//        directionRepository.create(direction);


        PassportRepositoryImpl passportRepository = new PassportRepositoryImpl();
        List<Passport> passports;
        Passport passport = new Passport();

        // READ to List ALL passports from bd
        passports = passportRepository.read();
        passports.forEach(System.out::println);

        // CREATE NEW passport insert to bd
        passport.setNumber((int) (Math.random() * 313131));
        System.out.println(passport);
        passportRepository.create(passport);
        System.out.println(passport);

        // UPDATE passport in bd
        passport.setId(3L);
        passport.setNumber((int) (Math.random() * 313131));
        System.out.println(passport);
        passportRepository.update(passport);
        System.out.println(passport);

        // READ to print ALL passports from bd
        passportRepository.read().forEach(System.out::println);

        // PRINT List objects pasports
        System.out.println("\nList passports:");
        passports.forEach(System.out::println);

        // DELETE by id passport
        Long id = 33L; // ид в бд удаляемого элемента
        System.out.println("id=" + id);
        passportRepository.deleteById(id);
        List<Passport> finalPassports = passports;
        Passport pById = passports.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .get();
        passports.remove(passports.indexOf(pById)); // delete passport from List objects by number

        // DELETE by number passport
        Integer number = 184765;
        System.out.println("number=" + number);
        passportRepository.deleteByNumber(number); // delete passport from bd by number
        Passport pByNumber = passports.stream()
                .filter(p -> number.equals(p.getNumber()))
                .findFirst()
                .get();
        passports.remove(passports.indexOf(pByNumber)); // delete passport from List objects by number

        // PRINT list objects pasports
        System.out.println("\nList passports:");
        passports.forEach(System.out::println);

        // READ to print ALL passports from bd
        passportRepository.read().forEach(System.out::println);


        // CREATE passenger
//        Passenger passenger = new Passenger();
//        passenger.setName("Name1");
//        passenger.setPassport(passport);
//        System.out.println(passenger);
//        PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl();
//        passengerRepository.create(passenger);
//        System.out.println(passenger);

    }
}