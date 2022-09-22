package com.solvd.airport.persistance;

import com.solvd.airport.domain.flight.Ticket;

import java.util.List;

public interface TicketRepository {

    void create(Ticket ticket);

    List<Ticket> readAll();

    Ticket readById(Long id);

    List<Ticket> readByFlightId(Long flightId);

    void update(Ticket ticket);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}