package com.solvd.airport;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
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

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        AircarrierRepository aircarrierRepository = new AircarrierRepositoryImpl();
        List<Aircarrier> aircarriers;
        Aircarrier aircarrier;

        System.out.println("\nMain: READ all aircarriers");
        aircarriers = aircarrierRepository.readAll();
        aircarriers.forEach(System.out::println);

        System.out.println("\nMain: CREATE new aircarrier");
        Aircarrier aircarrierNew = new Aircarrier();
        aircarrierNew.setName("NewCarrier66");
        aircarrierRepository.create(aircarrierNew);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierRepository.readAll().forEach(System.out::println);

        System.out.println("\nMain: UPDATE aircarrier");
        aircarrierNew.setName("UpdateCarrier66");
        aircarrierRepository.update(aircarrierNew);

        System.out.println("\nMain: READ aircarrier by ID");
        Long newId = aircarrierNew.getId();
        aircarrier = aircarrierRepository.readById(newId);
        System.out.println("Aircarrier: " + aircarrier);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierRepository.readAll().forEach(System.out::println);

        System.out.println("\nMain: DELETE aircarrier");
        aircarrierRepository.deleteById(newId);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierRepository.readAll().forEach(System.out::println);

        System.out.println("\nMain: READ all aircrafts");
        AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
        List<Aircraft> aircrafts = aircraftRepository.readAll();
        aircrafts.forEach(System.out::println);

        System.out.println("\nMain: READ all airports");
        AirportRepository airportRepository = new AirportRepositoryImpl();
        List<Airport> airports = airportRepository.readAll();
        airports.forEach(System.out::println);

        System.out.println("\nMain: READ all airstrips");
        AirstripRepository airstripRepository = new AirstripRepositoryImpl();
        List<Airstrip> airstrips = airstripRepository.readAll();
        airstrips.forEach(System.out::println);

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
    }
}