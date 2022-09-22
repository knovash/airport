package com.solvd.airport.persistance;

import com.solvd.airport.domain.port.Airport;

import java.util.List;

public interface AirportRepository {

    void create(Airport airport);

    List<Airport> readAll();

    Airport readById(Long id);

    void update(Airport airport);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}