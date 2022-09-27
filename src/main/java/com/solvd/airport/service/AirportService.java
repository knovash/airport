package com.solvd.airport.service;

import com.solvd.airport.domain.port.Airport;

import java.util.List;

public interface AirportService {

    Airport create(Airport airport);

    List<Airport> readAll();
}