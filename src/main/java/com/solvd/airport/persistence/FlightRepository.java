package com.solvd.airport.persistence;

import com.solvd.airport.domain.flight.Flight;

import java.util.List;

public interface FlightRepository {

    void create(Flight flight, Long aircarrierId);

    List<Flight> readAll();

    Flight readById(Long id);

    void update(Flight flight);

    void deleteById(Long id);
}
