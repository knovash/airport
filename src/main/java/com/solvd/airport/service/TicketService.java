package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket, Long flightId);

    List<Ticket> readAll();

    Ticket readById(Long id);

    void update(Ticket ticket);

    void deleteById(Long id);
}
