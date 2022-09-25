package com.solvd.airport.service.impl;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistance.FlightRepository;
import com.solvd.airport.persistance.impl.FlightRepositoryImpl;
import com.solvd.airport.service.FlightService;
import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.PilotService;
import com.solvd.airport.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final TicketService ticketService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.ticketService = new TicketServiceImpl();
    }

    @Override
    public Flight create(Flight flight, Long aircarrier_id) {
        flight.setId(null);
        flightRepository.create(flight); // pilotRepository в persistance там sql insert зааносит информацию из полей в бд

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
}
