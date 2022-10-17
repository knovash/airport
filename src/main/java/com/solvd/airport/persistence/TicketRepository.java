package com.solvd.airport.persistence;

import com.solvd.airport.domain.flight.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    void create(@Param("ticket") Ticket ticket, @Param("flightId") Long flightId);

    List<Ticket> readAll();

    Optional<Ticket> readById(Long id);

    void update(@Param("ticket") Ticket ticket, @Param("flightId") Long flightId);

    void deleteById(Long id);
}
