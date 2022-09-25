package com.solvd.airport;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.License;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.impl.*;
import com.solvd.airport.service.DirectionService;
import com.solvd.airport.service.impl.DirectionServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        System.out.println("\nMain: READ all aircarriers");
        AircarrierRepository aircarrierRepository = new AircarrierRepositoryImpl();
        List<Aircarrier> aircarriers = aircarrierRepository.readAll();
        aircarriers.forEach(System.out::println);
//
        System.out.println("\nMain: READ all aircrafts");
        AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
        List<Aircraft> aircrafts = aircraftRepository.readAll();
        aircrafts.forEach(System.out::println);

//        System.out.println("\nMain: READ all airports");
//        AirportRepository airportRepository = new AirportRepositoryImpl();
//        List<Airport> airports = airportRepository.readAll();
//        airports.forEach(System.out::println);

        System.out.println("\nMain: READ all airstrips");
        AirstripRepository airstripRepository = new AirstripRepositoryImpl();
        List<Airstrip> airstrips = airstripRepository.readAll();
        airstrips.forEach(System.out::println);

//        System.out.println("\nMain: CREATE direction");
//        DirectionService directionService = new DirectionServiceImpl();
//        Direction d = new Direction();
//        d.setCountry("newcountry");
//        d.setDistance(new BigDecimal(999));
//        directionService.create(d);

        System.out.println("\nMain: READ all directions");
        DirectionRepository directionRepository = new DirectionRepositoryImpl();
        List<Direction> directions = directionRepository.readAll();
        directions.forEach(System.out::println);

        System.out.println("\nMain: READ all flights");
        FlightRepository flightRepository = new FlightRepositoryImpl();
        List<Flight> flights = flightRepository.readAll();
        flights.forEach(System.out::println);

        System.out.println("\nMain: READ all gates");
        GateRepository gateRepository = new GateRepositoryImpl();
        List<Gate> gates = gateRepository.readAll();
        gates.forEach(System.out::println);

        System.out.println("\nMain: READ all passports");
        PassportRepository passportRepository = new PassportRepositoryImpl();
        List<Passport> passports = passportRepository.readAll();
        passports.forEach(System.out::println);

        System.out.println("\nMain: READ all passengers");
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        List<Passenger> passengers = passengerRepository.readAll();
        passengers.forEach(System.out::println);

        System.out.println("\nMain: READ all pilots");
        PilotRepository pilotRepository = new PilotRepositoryImpl();
        List<Pilot> pilots = pilotRepository.readAll();
        pilots.forEach(System.out::println);

        System.out.println("\nMain: READ all tickets");
        TicketRepository ticketRepository = new TicketRepositoryImpl();
        List<Ticket> tickets = ticketRepository.readAll();
        tickets.forEach(System.out::println);


        // CREATE NEW passport insert to bd
//        System.out.println("\nMain: CREATE passport");
//        passport.setNumber((int) (Math.random() * 313131));
//        passportRepository.create(passport);
//        System.out.println(passport);

//        // UPDATE passport in bd
//        id = 26L;
//        passport.setId(id);
//        passport.setNumber((int) (Math.random() * 313131));
//        System.out.println(passport);
//        passportRepository.update(passport);
//        System.out.println(passport);
//
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

    }
}