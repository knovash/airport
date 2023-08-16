package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketService {

    Ticket create(@Param("ticket") Ticket ticket, @Param("flightId") Long flightId);

    List<Ticket> readAll();

    Ticket readById(Long id);

    void update(@Param("ticket") Ticket ticket, @Param("flightId") Long flightId);

    void deleteById(Long id);
}
