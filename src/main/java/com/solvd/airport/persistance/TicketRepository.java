package com.solvd.airport.persistance;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TicketRepository {

    void create(Ticket ticket);

    List<Ticket> readAll();

    Ticket map(ResultSet resultSet) throws SQLException;

    Ticket readById(Long id);

    List<Ticket> readByFlightId(Long flightId);

    void update(Ticket ticket);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}