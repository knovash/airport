package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.FlightRepository;
import com.solvd.airport.persistence.impl.FlightRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PassengerService passengerService;
    private final TicketService ticketService;
    private final PilotService pilotService;
    private final AirstripService airstripService;
    private final AircraftService aircraftService;
    private final DirectionService directionService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.passengerService = new PassengerServiceImpl();
        this.ticketService = new TicketServiceImpl();
        this.pilotService = new PilotServiceImpl();
        this.airstripService = new AirstripServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.directionService = new DirectionServiceImpl();
    }

    @Override
    public Flight create(Flight flight, Long aircarrierId) {
        System.out.println("SERVICE create flight");
        flight.setId(null);
        if (flight.getPilot() != null) {
            Pilot pilot = flight.getPilot();
            if (pilot.getId() == null) {
                pilotService.create(pilot, aircarrierId);
            }
            else {
                pilotService.update(pilot, aircarrierId);
            }
        }
        if (flight.getAircraft() != null) {
            Aircraft aircraft = flight.getAircraft();
            if (aircraft.getId() == null) {
                aircraftService.create(aircraft, aircarrierId);
            }
            else {
                aircraftService.update(aircraft, aircarrierId);
            }
        }
        if (flight.getDirection() != null) {
            Direction direction = flight.getDirection();
            if (direction.getId() == null) {
                directionService.create(direction);
            }
            else {
                directionService.update(direction);
            }
        }
        if (flight.getAirstrip() != null) {
            Airstrip airstrip = flight.getAirstrip();
            if (airstrip.getId() == null) {
                airstripService.create(airstrip, 1L);
            }
            else {
                airstripService.update(airstrip, 1L);
            }
        }
        flightRepository.create(flight, aircarrierId);
        if (flight.getTickets() != null) {
            flight.getTickets()
                    .forEach(ticket -> {
                        if (ticket.getId() == null) {
                            ticketService.create(ticket, flight.getId());
                        }
                        else {
                            ticketService.update(ticket, flight.getId());
                        }
                    });
        }
        return flight;
    }

    @Override
    public List<Flight> readAll() {
        System.out.println("SERVICE readAll flight");
        return flightRepository.readAll();
    }

    @Override
    public Flight readById(Long id) {
        System.out.println("SERVICE readById flight");
        return flightRepository.readById(id)
                .orElseThrow(() -> new NotFound("Flight with id=" + id + " not found"));
    }

    @Override
    public void update(Flight flight, Long aircarrierId) {
        System.out.println("SERVICE update flight");

        if (flight.getPilot() != null) {
            Pilot pilot = flight.getPilot();
            if (pilot.getId() == null) {
                pilotService.create(pilot, aircarrierId);
            }
            else {
                pilotService.update(pilot, aircarrierId);
            }
        }
        if (flight.getAircraft() != null) {
            Aircraft aircraft = flight.getAircraft();
            if (aircraft.getId() == null) {
                aircraftService.create(aircraft, aircarrierId);
            }
            else {
                aircraftService.update(aircraft, aircarrierId);
            }
        }
        if (flight.getDirection() != null) {
            Direction direction = flight.getDirection();
            if (direction.getId() == null) {
                directionService.create(direction);
            }
            else {
                directionService.update(direction);
            }
        }
        if (flight.getAirstrip() != null) {
            Airstrip airstrip = flight.getAirstrip();
            if (airstrip.getId() == null) {
                airstripService.create(airstrip, 1L);
            }
            else {
                airstripService.update(airstrip, 1L);
            }
        }



        if (flight.getTickets() != null) {
            flight.getTickets()
                    .forEach(ticket -> {
                        if (ticket.getId() == null) {
                            ticketService.create(ticket, flight.getId());
                        }
                        else {
                            ticketService.update(ticket, flight.getId());
                        }
                    });
        }

        flightRepository.update(flight, aircarrierId);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById flight");
        flightRepository.deleteById(id);
    }
}
