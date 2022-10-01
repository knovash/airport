package com.solvd.airport.service.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.TicketRepository;
import com.solvd.airport.persistence.impl.TicketRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final PassengerService passengerService;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.passengerService = new PassengerServiceImpl();
    }

//    private Long id;
//    private Passenger passenger;
//    private BigDecimal price;
//    private Integer number;
//    private Integer seat;

    @Override
    public Ticket create(Ticket ticket, Long flightId) {
        ticket.setId(null);


        if (ticket.getPassenger() != null) {
            Passenger passenger = ticket.getPassenger();
            passengerService.create(passenger);
            ticket.setPassenger(passenger);
        }
        ticketRepository.create(ticket, flightId);
        return ticket;
    }

    @Override
    public List<Ticket> readAll() {
        return ticketRepository.readAll();
    }

    @Override
    public Ticket readById(Long id) {
        return null;
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
