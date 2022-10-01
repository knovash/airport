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
import com.solvd.airport.persistence.*;
import com.solvd.airport.persistence.impl.*;
import com.solvd.airport.service.*;
import com.solvd.airport.service.impl.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
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

//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        AircarrierService aircarrierService = new AircarrierServiceImpl();
        AircraftService aircraftService = new AircraftServiceImpl();
        AirportService airportService = new AirportServiceImpl();
        AirstripService airstripService = new AirstripServiceImpl();
        DirectionService directionService = new DirectionServiceImpl();
        FlightService flightService = new FlightServiceImpl();
        PassengerService passengerService = new PassengerServiceImpl();
        PassportService passportService = new PassportServiceImpl();
        PilotService pilotService = new PilotServiceImpl();
        TicketService ticketService = new TicketServiceImpl();
        AircarrierRepository aircarrierRepository = new AircarrierRepositoryImpl();

//        System.out.println("\nSERVICE CREATE Aircarrier1: Pilots, Aircrafts");
//
//        Aircarrier aircarrier1 = new Aircarrier();
//        aircarrier1.setName("carrier" + (int) (Math.random() * 33133));
//        aircarrier1.setPilots(Arrays.asList(pilot1, pilot2));
//        aircarrier1.setAircrafts(Arrays.asList(aircraft1, aircraft2));
////   / ---    aircarrier1 = aircarrierService.create(aircarrier1);
////   /     System.out.println(aircarrier1);
//
//        System.out.println("\nSERVICE CREATE Aircarrier2: Pilots, Aircrafts");
//
//        Pilot pilot11 = new Pilot();
//        pilot11.setName("NewPilot" + (int) (Math.random() * 31333));
//        Pilot pilot12 = new Pilot();
//        pilot12.setName("NewPilot" + (int) (Math.random() * 33313));
////    /    System.out.println(pilot11);
////   /     System.out.println(pilot12);
//
//        Aircraft aircraft11 = new Aircraft();
//        aircraft11.setModel("NewAircraft" + (int) (Math.random() * 33133));
//        aircraft11.setNumber((int) (Math.random() * 33133));
//        Aircraft aircraft12 = new Aircraft();
//        aircraft12.setModel("NewAircraft" + (int) (Math.random() * 33133));
//        aircraft12.setNumber((int) (Math.random() * 31333));
////    /    System.out.println(aircraft11);
////   /     System.out.println(aircraft12);
//
//        Aircarrier aircarrier11 = new Aircarrier();
//        aircarrier11.setName("carrier" + (int) (Math.random() * 33133));
//        aircarrier11.setPilots(Arrays.asList(pilot11, pilot12));
//        aircarrier11.setAircrafts(Arrays.asList(aircraft11, aircraft12));
//
////   / ---    aircarrier11 = aircarrierService.create(aircarrier11);
////   /     System.out.println(aircarrier11);

//        System.out.println("\nSERVICE CREATE Airport: Aircarriers, Airstrips, Gates");
//
//        Airstrip airstrip1 = new Airstrip();
//        airstrip1.setNumber((int) (Math.random() * 33313));
//        Airstrip airstrip2 = new Airstrip();
//        airstrip2.setNumber((int) (Math.random() * 31333));
////   /     System.out.println(airstrip1);
////   /     System.out.println(airstrip2);
//
//        Airport airport1 = new Airport();
//        airport1.setName("airport" + (int) (Math.random() * 31333));
//        airport1.setAirstrips(Arrays.asList(airstrip1, airstrip1));
//        airport1.setAircarriers(Arrays.asList(aircarrier1, aircarrier11));
//
//        System.out.println("+++++++++++++++++\n");
//        airport1 = airportService.create(airport1);
//        System.out.println("=================");
//        System.out.println(airport1);
//        System.out.println("++++++++++++++++\n");


        // PASSPORT SERVICES
        System.out.println("\nPASSPORT SERVICES\n");

        Passport passport1 = new Passport();
        passport1.setNumber((int) (Math.random() * 31333));

        Passport passport2 = new Passport();
        passport2.setNumber((int) (Math.random() * 31333));

        System.out.println(passport1 + "\n" + passport2);

        passport1 = passportService.create(passport1);
        System.out.println("\npassport created\n" + passport1);

        System.out.println("\npassports read all\n");
        passportService.readAll().forEach(System.out::println);

        passport1.setNumber((int) (Math.random() * 31333));
        passportService.update(passport1);
        System.out.println("\npassport updated\n" + passport1);

        passport1 = passportService.readById(passport1.getId());
        System.out.println("\npassport read by id\n" + passport1);

        System.out.println("\npassport delete by id\n");
        passportService.deleteById(passport1.getId());
        System.out.println(passport1);

        System.out.println("\npassports read all\n");
        passportService.readAll().forEach(System.out::println);

//        System.exit(0);

        // PASSENGER SERVICES
        System.out.println("\nPASSENGER SERVICES\n");

        Passenger passenger1 = new Passenger();
        passenger1.setName("NewName" + (int) (Math.random() * 31333));
        passenger1.setPassport(passport1);
        System.out.println(passenger1);

        Passenger passenger2 = new Passenger();
        passenger2.setName("NewName" + (int) (Math.random() * 31333));
        passenger2.setPassport(passport2);
        System.out.println(passenger2);

        System.out.println("\npassenger to create\n" + passenger2);
        passenger2 = passengerService.create(passenger2);
        System.out.println("\npassenger created\n" + passenger2);

        passenger2 = passengerService.readById(passenger2.getId());
        System.out.println("\npassenger read by id\n" + passenger2);

        passenger2.setName("UpdName" + (int) (Math.random() * 31333));
        passengerService.update(passenger2);
        System.out.println("\npassenger updated\n" + passenger2);

        System.out.println("\npassengers read all\n");
        passengerService.readAll().forEach(System.out::println);

//        passportService.deleteById(passenger2.getId()-1);
//        System.out.println("\npassenger2 delete by id\n" + passenger2);

//        System.exit(0);

        // DIRECTION SERVICES
        System.out.println("\nDIRECTION SERVICES\n");

        Direction direction1 = new Direction();
        direction1.setCountry("Country" + (int) (Math.random() * 31333));
        direction1.setDistance(BigDecimal.valueOf((int) (Math.random() * 31333)));

        Direction direction2 = new Direction();
        direction2.setCountry("Country" + (int) (Math.random() * 31333));
        direction2.setDistance(BigDecimal.valueOf((int) (Math.random() * 31333)));

        System.out.println(direction1 + "\n" + direction2);

        direction1 = directionService.create(direction1);
        System.out.println("\ndirection created\n" + direction1);

        System.out.println("\ndirections read all\n");
        directionService.readAll().forEach(System.out::println);

        direction1.setCountry("UpdCountry" + (int) (Math.random() * 31333));
        direction1.setDistance(BigDecimal.valueOf((int) (Math.random() * 31333)));
        directionService.update(direction1);
        System.out.println("\ndirection updated\n" + direction1);

        direction1 = directionService.readById(direction1.getId());
        System.out.println("\ndirection read by id\n" + direction1);

        System.out.println("\ndirection delete by id\n");
        directionService.deleteById(direction1.getId());
        System.out.println(direction1);

        System.out.println("\ndirections read all\n");
        directionService.readAll().forEach(System.out::println);

//        System.exit(0);

        // AIRCRAFT SERVICES
        System.out.println("\nAIRCRAFT SERVICES\n");

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setModel("model" + (int) (Math.random() * 31333));
        aircraft1.setNumber((int) (Math.random() * 31333));

        Aircraft aircraft2 = new Aircraft();
        aircraft2.setModel("model" + (int) (Math.random() * 31333));
        aircraft2.setNumber((int) (Math.random() * 31333));

        System.out.println(aircraft1 + "\n" + aircraft2);

//        aircraft1 = aircraftService.create(aircraft1, 1L);
//        System.out.println("\naircraft created\n" + aircraft1);
//
        System.out.println("\naircrafts read all\n");
        aircraftService.readAll().forEach(System.out::println);
//
//        aircraft1.setModel("model" + (int) (Math.random() * 31333));
//        aircraft1.setNumber((int) (Math.random() * 31333));
//        aircraftService.update(aircraft1, 1L);
//        System.out.println("\naircraft updated\n" + aircraft1);
//
//        aircraft1 = aircraftService.readById(aircraft1.getId());
//        System.out.println("\naircraft read by id\n" + aircraft1);
//
//        System.out.println("\naircraft delete by id\n");
//        System.out.println(aircraft1);
//        aircraftService.deleteById(aircraft1.getId());
//        System.out.println(aircraft1);
//
//        System.out.println("\naircrafts read all\n");
//        aircraftService.readAll().forEach(System.out::println);

//        System.exit(0);

        // PILOT SERVICES
        Pilot pilot1 = new Pilot();
        pilot1.setName("name" + (int) (Math.random() * 31333));

        Pilot pilot2 = new Pilot();
        pilot2.setName("name" + (int) (Math.random() * 31333));

        System.out.println(pilot1 + "\n" + pilot2);

//        pilot1 = pilotService.create(pilot1, 1L);
//        System.out.println("\npilot created\n" + pilot1);
//
//        System.out.println("\npilots read all\n");
//        pilotService.readAll().forEach(System.out::println);
//
//        pilot1.setName("name" + (int) (Math.random() * 31333));
//        pilotService.update(pilot1, 1L);
//        System.out.println("\npilot updated\n" + pilot1);
//
//        pilot1 = pilotService.readById(pilot1.getId());
//        System.out.println("\n------pilot read by id\n" + pilot1);
//
//        System.out.println("\npilot delete by id\n");
//        System.out.println(pilot1);
//        pilotService.deleteById(pilot1.getId());
//        System.out.println(pilot1);
//
//        System.out.println("\npilots read all\n");
//        pilotService.readAll().forEach(System.out::println);
//
//        System.exit(0);

        // AIRCARRIER SERVICES

        Aircarrier aircarrier1 = new Aircarrier();
        aircarrier1.setName("carrier" + (int) (Math.random() * 33133));
        aircarrier1.setPilots(Arrays.asList(pilot1, pilot2));
        aircarrier1.setAircrafts(Arrays.asList(aircraft1, aircraft2));

        aircarrier1 = aircarrierService.create(aircarrier1);
        System.out.println("\naircarrier created\n" + aircarrier1);

        System.out.println("\naircarriers read all\n");
        aircarrierService.readAll().forEach(System.out::println);
//
//        aircarrier1.setName("name" + (int) (Math.random() * 31333));
//        aircarrierService.update(aircarrier1);
//        System.out.println("\naircarrier updated\n" + aircarrier1);
//
//        aircarrier1 = aircarrierService.readById(aircarrier1.getId());
//        System.out.println("\n------aircarrier read by id\n" + aircarrier1);
//
//        System.out.println("\naircarrier delete by id\n");
//        System.out.println(aircarrier1);
//        aircarrierService.deleteById(aircarrier1.getId());
//        System.out.println(aircarrier1);
//
//        System.out.println("\naircarriers read all\n");
//        aircarrierService.readAll().forEach(System.out::println);

        System.exit(0);

        Ticket ticket1 = new Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setNumber((int) (Math.random() * 313));
        ticket1.setSeat((int) (Math.random() * 318));
        ticket1.setPrice(BigDecimal.valueOf((int) (Math.random() * 313)));

//        ticket1 = ticketService.create(ticket1, 1L);
//        System.out.println(ticket1);

//        Direction direction1 = new Direction();
//        direction1.setCountry("NewDirection");
//        direction1.setDistance(BigDecimal.valueOf(999));

//        Flight flight1 = new Flight();
//        flight1.setTickets(Arrays.asList(ticket1));
//        flight1.setDirection(direction1);
//        flight1.setAirstrip(airstrip1);
//        flight1.setNumber((int) (Math.random() * 313));
//        flight1.setAircraft(aircraft1);
//        flight1.setPilot(pilot1);
//        flight1.setDate(Date.valueOf(LocalDate.now()));
//        flight1.setAircarrier(aircarrier1);

//        System.out.println("\n-------flight--------------------------------------\n");
//        flight1 = flightService.create(flight1, 1L);
//        System.out.println(flight1);
//
//
//        System.out.println("---------------------------------------------\n");

//         AIRCARRIER READ
        List<Aircarrier> aircarriers;
        Aircarrier aircarrier;

        System.out.println("\nMain: READ all aircarriers");
        aircarriers = aircarrierService.readAll();
        aircarriers.forEach(System.out::println);

        System.out.println("\nMain: CREATE new aircarrier");
        Aircarrier aircarrierNew = new Aircarrier();
        aircarrierNew.setName("NewCarrier" + (int) (Math.random() * 333));
        aircarrierRepository.create(aircarrierNew);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierService.readAll().forEach(System.out::println);

        System.out.println("\nMain: UPDATE aircarrier");
        aircarrierNew.setName("UpdateCarrier" + (int) (Math.random() * 333));
        aircarrierRepository.update(aircarrierNew);

        System.out.println("\nMain: READ aircarrier by ID");
        Long newId = aircarrierNew.getId();
        aircarrier = aircarrierRepository.readById(newId);
        System.out.println("Aircarrier: " + aircarrier);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierService.readAll().forEach(System.out::println);

        System.out.println("\nMain: DELETE aircarrier");
        aircarrierRepository.deleteById(newId);

        System.out.println("\nMain: OUT all aircarriers");
        aircarrierService.readAll().forEach(System.out::println);

        // AIRCRAFT
        System.out.println("\nMain: READ all aircrafts");
        List<Aircraft> aircrafts = aircraftService.readAll();
        aircrafts.forEach(System.out::println);

        System.out.println("\nMain: READ all airports");
        List<Airport> airports = airportService.readAll();
        airports.forEach(System.out::println);

        System.out.println("\nMain: READ all airstrips");
        List<Airstrip> airstrips = airstripService.readAll();
        airstrips.forEach(System.out::println);

        System.out.println("\nMain: READ all directions");
        List<Direction> directions = directionService.readAll();
        directions.forEach(System.out::println);

        System.out.println("\nMain: READ all flights");
        List<Flight> flights = flightService.readAll();
        flights.forEach(System.out::println);

//        System.out.println("\nMain: READ all passports");
//        List<Passport> passports = passportService.readAll();
//        passports.forEach(System.out::println);

//        System.out.println("\nMain: READ all passengers");
//        List<Passenger> passengers = passengerService.readAll();
//        passengers.forEach(System.out::println);

        System.out.println("\nMain: READ all pilots");
        List<Pilot> pilots = pilotService.readAll();
        pilots.forEach(System.out::println);

        System.out.println("\nMain: READ all tickets");
        List<Ticket> tickets = ticketService.readAll();
        tickets.forEach(System.out::println);

    }
}
