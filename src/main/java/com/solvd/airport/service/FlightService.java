package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Flight;

import java.util.List;

public interface FlightService {

    Flight create(Flight flight, Long aircarrierId);

    List<Flight> readAll();
}