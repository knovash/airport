package com.solvd.airport.persistence;

import com.solvd.airport.domain.port.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    void create(Airport airport);

    List<Airport> readAll();

    Optional<Airport> readById(Long id);

    void update(Airport airport);

    void deleteById(Long id);
}
