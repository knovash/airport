package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.flight.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface FlightRepository {

    void create(Flight flight);

    List<Flight> readAll();

    Flight readById(Long id);

    void update(Flight flight);

    void deleteById(Long id);
}