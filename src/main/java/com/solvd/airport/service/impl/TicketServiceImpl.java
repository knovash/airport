package com.solvd.airport.service.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistance.TicketRepository;
import com.solvd.airport.persistance.impl.TicketRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final FlightService flightService;
    private final PassengerService passengerService;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.flightService = new FlightServiceImpl();
        this.passengerService = new PassengerServiceImpl();
    }

//    private Long id;
//    private Flight flight;
//    private Passenger passenger;
//    private Gate gate;
//    private BigDecimal price;
//    private Integer number;
//    private Integer seat;

    @Override
    public Ticket create(Ticket ticket, Long flightId) {
        ticket.setId(null);
        ticketRepository.create(ticket, flightId); // pilotRepository в persistance там sql insert зааносит информацию из полей в бд

        if (ticket.getPassenger() != null) {
            Passenger passenger = ticket.getPassenger();
            passengerService.create(passenger, ticket.getId());
            ticket.setPassenger(passenger);
        }

        return ticket;
    }

    @Override
    public List<Ticket> readAll() {
        return ticketRepository.readAll();
    }
}
