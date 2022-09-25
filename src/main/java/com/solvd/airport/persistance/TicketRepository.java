package com.solvd.airport.persistance;

import com.solvd.airport.domain.flight.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TicketRepository {

    void create(Ticket ticket);

    List<Ticket> readAll();

    Ticket map(ResultSet resultSet) throws SQLException;

    Ticket readById(Long id);

    void update(Ticket ticket);

    void deleteById(Long id);
}