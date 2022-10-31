package com.solvd.airport;

import com.solvd.airport.domain.carrier.*;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.human.FactoryHuman;
import com.solvd.airport.domain.human.HumanType;
import com.solvd.airport.domain.listener.EventHolder;
import com.solvd.airport.domain.listener.EventType;
import com.solvd.airport.domain.listener.IEvent;
import com.solvd.airport.domain.listener.User;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.domain.port.AirportAircarrier;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.*;
import com.solvd.airport.service.*;
import com.solvd.airport.service.impl.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * the factory pattern is used to create passengers or pilots with a random name
 * the builder pattern is used to create aircrafts
 */

public class Main {

    public static void main(String[] args) {

        Config config = new Config();
        config.getFromFile();
        String driver = Config.getDriver();
        String url = Config.getUrl();
        String username = Config.getUsername();
        String password = Config.getPassword();
        Integer poolsize = Config.getPoolsize();
        System.out.println("driver: " + driver + "\nurl: " + url + "\nusername: " + username + "\npassword: " + password + "\npoolsise: " + poolsize);

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
        AirportAircarrierService airportAircarrierService = new AirportAircarrierServiceImpl();

        System.out.println("\n--- READ FROM DB ---");

        User ivan1 = new User();
        User ivan2 = new User();
        User ivan3 = new User();
        ivan1.setFirstName("ivan1");
        ivan2.setFirstName("ivan2");
        ivan3.setFirstName("ivan3");
        EventHolder.subscribe(ivan1, EventType.MESSAGE);
        EventHolder.subscribe(ivan2, EventType.MESSAGE);
        EventHolder.subscribe(ivan3, EventType.MESSAGE);

        System.out.println("----");
        Passenger passenger11 = new Passenger();
        Passenger passenger12 = new Passenger();
        passenger11.setName("ssssss");
        passenger12.setName("ffffff");
        EventHolder.subscribe(passenger11, EventType.MESSAGE);
        EventHolder.subscribe(passenger12, EventType.MESSAGE);

        EventHolder.notify(EventType.MESSAGE);





        System.exit(0);

        System.out.println("\nPASSPORTS READ\n");
        System.out.println(passportService.readById(1L));
        passportService.readAll().forEach(System.out::println);
        System.out.println("\nPASSENGERS READ\n");
        System.out.println(passengerService.readById(1L));
        passengerService.readAll().forEach(System.out::println);
        System.out.println("\nTICKETS READ\n");
        System.out.println(ticketService.readById(1L));
        ticketService.readAll().forEach(System.out::println);
        System.out.println("\nDIRECTONS READ\n");
        System.out.println(directionService.readById(1L));
        directionService.readAll().forEach(System.out::println);
        System.out.println("\nPILOTS READ\n");
        System.out.println(pilotService.readById(1L));
        pilotService.readAll().forEach(System.out::println);
        System.out.println("\nAIRCRAFTS READ\n");
        System.out.println(aircraftService.readById(1L));
        aircraftService.readAll().forEach(System.out::println);
        System.out.println("\nAIRSTRIPS READ\n");
        System.out.println(airstripService.readById(1L));
        airstripService.readAll().forEach(System.out::println);
        System.out.println("\nFLIGHTS READ\n");
        System.out.println(flightService.readById(1L));
        flightService.readAll().forEach(System.out::println);
        System.out.println("\nAIRCARRIERS READ\n");
        System.out.println(aircarrierService.readById(1L));
        aircarrierService.readAll().forEach(System.out::println);
        System.out.println("\nAIRPORTS READ\n");
        System.out.println(airportService.readById(1L));
        airportService.readAll().forEach(System.out::println);

        System.exit(0);

        System.out.println("\n--- SET OBJECTS ---\n");

        Passport passport1 = new Passport();
        passport1.setNumber((int) (Math.random() * 3333));
        Passport passport2 = new Passport();
        passport2.setNumber((int) (Math.random() * 3333));
        System.out.println(passport1 + "\n" + passport2);


        Passenger passenger1 = (Passenger) FactoryHuman.getHuman(HumanType.PASSENGER);
        /**
         * code replaced by a pattern factory
         * Passenger passenger1 = new Passenger();
         * passenger1.setName("NewName" + (int) (Math.random() * 3333));
         */
        passenger1.setPassport(passport1);
        Passenger passenger2 = (Passenger) FactoryHuman.getHuman(HumanType.PASSENGER);
        passenger2.setPassport(passport2);
        System.out.println(passenger1 + "\n" + passenger2);

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(BigDecimal.valueOf((int) (Math.random() * 3333)));
        ticket1.setSeat((int) (Math.random() * 3333));
        ticket1.setPassenger(passenger1);
        Ticket ticket2 = new Ticket();
        ticket2.setPrice(BigDecimal.valueOf((int) (Math.random() * 3333)));
        ticket2.setSeat((int) (Math.random() * 3333));
        ticket2.setPassenger(passenger2);
        System.out.println(ticket1 + "\n" + ticket2);

        Direction direction1 = new Direction();
        direction1.setCountry("Country" + (int) (Math.random() * 3333));
        direction1.setDistance(BigDecimal.valueOf((int) (Math.random() * 3333)));
        Direction direction2 = new Direction();
        direction2.setCountry("Country" + (int) (Math.random() * 3333));
        direction2.setDistance(BigDecimal.valueOf((int) (Math.random() * 3333)));
        System.out.println(direction1 + "\n" + direction2);

        Pilot pilot1 = (Pilot) FactoryHuman.getHuman(HumanType.PILOT);
        /**
        * code replaced by a pattern factory
        * Pilot pilot1 = new Pilot();
        * pilot1.setName("name" + (int) (Math.random() * 3333));
        */
        Pilot pilot2 = (Pilot) FactoryHuman.getHuman(HumanType.PILOT);
        System.out.println(pilot1 + "\n" + pilot2);
        Aircraft aircraft1 = new Aircraft();
        /**
        * code replaced by a pattern builder
        * aircraft1.setModel("model" + (int) (Math.random() * 3333));
        * aircraft1.setNumber((int) (Math.random() * 3333));
        */
        aircraft1.toBuilder()
                .model("model" + (int) (Math.random() * 3333))
                .number((int) (Math.random() * 3333))
                .build();
        Aircraft aircraft2 = new Aircraft();
        aircraft2.toBuilder()
                .model("model" + (int) (Math.random() * 3333))
                .number((int) (Math.random() * 3333))
                .build();

        System.out.println(aircraft1 + "\n" + aircraft2);

        Airstrip airstrip1 = new Airstrip();
        airstrip1.setNumber((int) (Math.random() * 3333));
        Airstrip airstrip2 = new Airstrip();
        airstrip2.setNumber((int) (Math.random() * 3333));
        System.out.println(airstrip1 + "\n" + airstrip2);

        Flight flight1 = new Flight();
        flight1.setNumber((int) (Math.random() * 3333));
        flight1.setDate(Date.valueOf(LocalDate.now()));
        flight1.setAircraft(aircraft1);
        flight1.setPilot(pilot1);
        flight1.setAirstrip(airstrip1);
        flight1.setDirection(direction1);
        flight1.setTickets(Arrays.asList(ticket1, ticket2));
        System.out.println(flight1);

        Aircarrier aircarrier1 = new Aircarrier();
        aircarrier1.setName("carrier" + (int) (Math.random() * 3333));
        aircarrier1.setPilots(Arrays.asList(pilot1, pilot2));
        aircarrier1.setAircrafts(Arrays.asList(aircraft1, aircraft2));
        aircarrier1.setFlights(List.of(flight1));
        System.out.println(aircarrier1);

        Airport airport1 = new Airport();
        airport1.setName("airport" + (int) (Math.random() * 3333));
        airport1.setAircarriers(List.of(aircarrier1));
        airport1.setAirstrips(Arrays.asList(airstrip1, airstrip2));
        System.out.println(airport1);

        System.out.println("\n--- SERVICES ---\n");

        passport1 = passportService.create(passport1);
        passport2 = passportService.create(passport2);
        System.out.println(passport1 + "\n" + passport2);
        System.out.println("passports read all\n");
        passportService.readAll().forEach(System.out::println);

        passenger1 = passengerService.create(passenger1);
        passenger2 = passengerService.create(passenger2);
        System.out.println("\npassenger created\n" + passenger1 + "\n" + passenger2);
        System.out.println("passengers read all\n");
        passengerService.readAll().forEach(System.out::println);

        ticket1 = ticketService.create(ticket1, 1L);
        ticket2 = ticketService.create(ticket2, 1L);
        System.out.println("\nticket created\n" + ticket1 + "\n" + ticket2);
        System.out.println("tickets read all\n");
        ticketService.readAll().forEach(System.out::println);

        direction1 = directionService.create(direction1);
        direction2 = directionService.create(direction2);
        System.out.println("\ndirection created\n" + direction1 + "\n" + direction2);
        System.out.println("directions read all\n");
        directionService.readAll().forEach(System.out::println);

        pilot1 = pilotService.create(pilot1, 1L);
        pilot2 = pilotService.create(pilot2, 1L);
        System.out.println("\npilot created\n" + pilot1 + "\n" + pilot2);
        System.out.println("pilots read all\n");
        pilotService.readAll().forEach(System.out::println);

        aircraft1 = aircraftService.create(aircraft1, 1L);
        aircraft2 = aircraftService.create(aircraft2, 1L);
        System.out.println("\naircraft created\n" + aircraft1 + "\n" + aircraft2);
        System.out.println("aircrafts read all\n");
        aircraftService.readAll().forEach(System.out::println);

        System.out.println("\nflight1\n" + flight1);
        flight1 = flightService.create(flight1, 1L);
        System.out.println("\nflight created\n" + flight1);
        System.out.println("flights read all\n");
        flightService.readAll().forEach(System.out::println);

        System.out.println(aircarrier1);
        aircarrier1 = aircarrierService.create(aircarrier1);
        System.out.println("\naircarriers read all\n");
        aircarrierService.readAll().forEach(System.out::println);

        System.out.println("\n---  A I R P O R T   C R E A T E  ---\n");

        airport1 = airportService.create(airport1);
        AirportAircarrier airportAircarrier1 = new AirportAircarrier();
        airportAircarrier1.setAirportId(airport1.getId());
        airportAircarrier1.setAircarrierId(aircarrier1.getId());
        airportAircarrierService.create(airportAircarrier1);
        System.out.println(airportService.readById(airport1.getId()));

        System.out.println("\n--- READ ALL AIPORTS ---");
        airportService.readAll().forEach(System.out::println);

        System.out.println("\n--- UPDATE OBJECTS ---\n");
        passport1.setNumber((int) (Math.random() * 3333) * 100);
        passport2.setNumber((int) (Math.random() * 3333 * 100));
        System.out.println(passport1);
        System.out.println(passport2);
        passportService.update(passport1);
        passportService.readAll().forEach(System.out::println);
        passenger1.setName("Upd Name" + (int) (Math.random() * 333) * 100);
        passenger1.setPassport(passport1);
        passenger2.setName("Upd Name" + (int) (Math.random() * 333) * 100);
        passenger2.setPassport(passport2);
        ticket1.setPrice(BigDecimal.valueOf((int) (Math.random() * 3333) * 100));
        ticket1.setSeat((int) (Math.random() * 3333) * 100);
        ticket1.setPassenger(passenger1);
        ticket2.setPrice(BigDecimal.valueOf((int) (Math.random() * 3333) * 100));
        ticket2.setSeat((int) (Math.random() * 3333) * 100);
        ticket2.setPassenger(passenger2);
        direction1.setCountry("Upd Country" + (int) (Math.random() * 3333) * 100);
        direction1.setDistance(BigDecimal.valueOf((int) (Math.random() * 333) * 100));
        direction2.setCountry("Upd Country" + (int) (Math.random() * 3333) * 100);
        direction2.setDistance(BigDecimal.valueOf((int) (Math.random() * 3333) * 100));
        pilot1.setName("Upd Name" + (int) (Math.random() * 3333) * 100);
        pilot2.setName("Upd Name" + (int) (Math.random() * 3333) * 100);
        aircraft1.toBuilder()
                .model("model" + (int) (Math.random() * 3333))
                .number((int) (Math.random() * 3333))
                .build();
        aircraft2.toBuilder()
                .model("model" + (int) (Math.random() * 3333))
                .number((int) (Math.random() * 3333))
                .build();
        airstrip1.setNumber((int) (Math.random() * 3333) * 100);
        airstrip2.setNumber((int) (Math.random() * 3333) * 100);
        flight1.setNumber((int) (Math.random() * 3333) * 100);
        flight1.setDate(Date.valueOf(LocalDate.now()));
        flight1.setAircraft(aircraft1);
        flight1.setPilot(pilot1);
        flight1.setAirstrip(airstrip1);
        flight1.setDirection(direction1);
        flight1.setTickets(Arrays.asList(ticket1, ticket2));
        aircarrier1.setName("Upd carrier" + (int) (Math.random() * 333) * 100);
        aircarrier1.setPilots(Arrays.asList(pilot1, pilot2));
        aircarrier1.setAircrafts(Arrays.asList(aircraft1, aircraft2));
        aircarrier1.setFlights(List.of(flight1));

        passengerService.update(passenger1);
        aircarrierService.update(aircarrier1);

        System.out.println("\n--- READ ALL AIPORTS UPDATED ---");
        airportService.readAll().forEach(System.out::println);
    }
}
