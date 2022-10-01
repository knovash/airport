package com.solvd.airport.service.impl;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistence.FlightRepository;
import com.solvd.airport.persistence.impl.FlightRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final AircarrierService aircarrierService;
    private final AirstripService airstripService;
    private final DirectionService directionService;
    private final TicketService ticketService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.aircarrierService = new AircarrierServiceImpl();
        this.airstripService = new AirstripServiceImpl();
        this.directionService = new DirectionServiceImpl();
        this.ticketService = new TicketServiceImpl();
    }

//    private Long id;
//    private Aircarrier aircarrier;
//    private Aircraft aircraft;
//    private Airstrip airstrip;
//    private Pilot pilot;
//    private Direction direction;
//    private Integer number;
//    private Date date;
//    private List<Ticket> tickets;

    @Override
    public Flight create(Flight flight, Long aircarrierId) {
        flight.setId(null);
        flightRepository.create(flight, aircarrierId);
        if (flight.getTickets() != null) {
            List<Ticket> tickets = flight.getTickets().stream()
                    .map(ticket -> ticketService.create(ticket, flight.getId()))
                    .collect(Collectors.toList());
            flight.setTickets(tickets);
        }

        return flight;
    }

    @Override
    public List<Flight> readAll() {
        return flightRepository.readAll();
    }

    @Override
    public Flight readById(Long id) {
        return null;
    }

    @Override
    public void update(Flight flight) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
