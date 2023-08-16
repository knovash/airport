package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.TicketRepository;
import com.solvd.airport.persistence.impl.TicketMapperImpl;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PassengerService passengerService;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketMapperImpl();
        this.passengerService = new PassengerServiceImpl();
    }

    @Override
    public Ticket create(Ticket ticket, Long flightId) {
        System.out.println("SERVICE create ticket.");
        ticket.setId(null);
        if (ticket.getPassenger() != null) {
            Passenger passenger = ticket.getPassenger();
            if (passenger.getId() == null) {
                passengerService.create(passenger);
            }
            else {
                passengerService.update(passenger);
            }
        }
        ticketRepository.create(ticket, flightId);
        return ticket;
    }

    @Override
    public List<Ticket> readAll() {
        System.out.println("SERVICE readAll ticket");
        return ticketRepository.readAll();
    }

    @Override
    public Ticket readById(Long id) {
        System.out.println("SERVICE readById ticket");
        return ticketRepository.readById(id)
                .orElseThrow(() -> new NotFound("Ticket with id=" + id + " not found"));
    }

    @Override
    public void update(Ticket ticket, Long flightId) {
        System.out.println("SERVICE update ticket.");
        if (ticket.getPassenger() != null) {
            Passenger passenger = ticket.getPassenger();
            if (passenger.getId() == null) {
                passengerService.create(passenger);
            }
            else {
                passengerService.update(passenger);
            }
        }
        System.out.println(" to update ticket: " + ticket + " flightId=" + flightId);
        ticketRepository.update(ticket, flightId);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById ticket");
        ticketRepository.deleteById(id);
    }
}
