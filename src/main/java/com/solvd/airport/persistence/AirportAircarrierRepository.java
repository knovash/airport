package com.solvd.airport.persistence;

import com.solvd.airport.domain.port.AirportAircarrier;

import java.util.List;
import java.util.Optional;

public interface AirportAircarrierRepository {

    void create(AirportAircarrier airportAircarrier);

    List<AirportAircarrier> readAll();

    Optional<AirportAircarrier> readById(Long id);

    void update(AirportAircarrier airportAircarrier);

    void deleteById(Long id);
}
