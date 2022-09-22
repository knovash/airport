package com.solvd.airport;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.Config;
import com.solvd.airport.persistance.impl.PassengerRepositoryImpl;
import com.solvd.airport.persistance.impl.PassportRepositoryImpl;

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

        PassportRepositoryImpl passportRepository = new PassportRepositoryImpl();
        List<Passport> passports;
        Passport passport = new Passport();
        Long id;

//        // READ to List ALL passports from bd
//        passports = passportRepository.readAll();
//        passports.forEach(System.out::println);
//
//        // READ by id passport from bd
//        passport = passportRepository.readById(3L);
//        System.out.println("passport by id: " + passport);
//
        // CREATE NEW passport insert to bd
        System.out.println("\nMain: CREATE passport");
        passport.setNumber((int) (Math.random() * 313131));
        passportRepository.create(passport);
        System.out.println(passport);

//        // UPDATE passport in bd
//        id = 26L;
//        passport.setId(id);
//        passport.setNumber((int) (Math.random() * 313131));
//        System.out.println(passport);
//        passportRepository.update(passport);
//        System.out.println(passport);
//
//        // READ to print ALL passports from bd
//        passportRepository.readAll().forEach(System.out::println);
//
//        // PRINT List objects pasports
//        System.out.println("\nList passports:");
//        passports.forEach(System.out::println);

        // DELETE by id passport
//        id = 39L; // ид в бд удаляемого элемента
//        System.out.println("id=" + id);
//        passportRepository.deleteById(id);
//        List<Passport> finalPassports = passports;
//        Passport pById = passports.stream()
//                .filter(p -> id.equals(p.getId()))
//                .findFirst()
//                .get();
//        passports.remove(passports.indexOf(pById)); // delete passport from List objects by number

        // DELETE by number passport
//        Integer number = 184765;
//        System.out.println("number=" + number);
//        passportRepository.deleteByNumber(number); // delete passport from bd by number
//        Passport pByNumber = passports.stream()
//                .filter(p -> number.equals(p.getNumber()))
//                .findFirst()
//                .get();
//        passports.remove(passports.indexOf(pByNumber)); // delete passport from List objects by number

//        // PRINT list objects pasports
//        System.out.println("\nList passports:");
//        passports.forEach(System.out::println);
//
//        // READ to print ALL passports from bd
//        passportRepository.readAll().forEach(System.out::println);





        PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl();
        List<Passenger> passengers;
        Passenger passenger = new Passenger();

        // READ to List ALL passengers from bd
        System.out.println("\nMain: READ all passengers");
        passengers = passengerRepository.readAll();
        passengers.forEach(System.out::println);

        // CREATE NEW passenger insert to bd
        System.out.println("\nMain: CREATE passenger");
        passenger.setName("IvanNEW");
        passenger.setPassport(passport);
        System.out.println(passenger);
        passengerRepository.create(passenger);
        System.out.println(passenger);

        // UPDATE passenger in bd
        System.out.println("\nMain: UPDATE passenger");
        id = 5L;
        passenger.setId(id);
        passenger.setName("NameUpdate");
        passenger.setPassport(passport);
        System.out.println(passenger);
        passengerRepository.update(passenger);
        System.out.println(passenger);

        // READ to print ALL passengers from bd
        System.out.println("\nMain: READ all passengers");
        passengerRepository.readAll().forEach(System.out::println);
    }
}