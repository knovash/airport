package com.solvd.airport.service;

import com.solvd.airport.domain.port.AirportAircarrier;

import java.util.List;

public interface AirportAircarrierService {

    AirportAircarrier create(AirportAircarrier airportAircarrier);

    List<AirportAircarrier> readAll();

    AirportAircarrier readById(Long id);

    void update(AirportAircarrier airportAircarrier);

    void deleteById(Long id);
}
