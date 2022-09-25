package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Flight;

import java.util.List;

public interface FlightService {

    Flight create(Flight flight, Long aircarrier_id);

    List<Flight> readAll();

}