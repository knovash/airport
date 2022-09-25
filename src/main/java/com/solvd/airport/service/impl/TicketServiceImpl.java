package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.TicketRepository;
import com.solvd.airport.persistance.impl.TicketRepositoryImpl;
import com.solvd.airport.service.TicketService;
import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.FlightService;
import com.solvd.airport.service.PilotService;

import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final FlightService flightService;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.flightService = new FlightServiceImpl();
    }

    @Override
    public Ticket create(Ticket ticket, Long flightId) {
        ticket.setId(null);
        ticketRepository.create(ticket); // pilotRepository в persistance там sql insert зааносит информацию из полей в бд

        if (ticket.getFlight() != null) {
            Flight flight = ticket.getFlight();
            flightService.create(flight, 1L);
            ticket.setFlight(flight);
        }


        return ticket;
    }

    @Override
    public List<Ticket> readAll() {
        return ticketRepository.readAll();
    }
}
