package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Aircraft;

import java.util.List;

public interface AircraftService {

    Aircraft create(Aircraft aircraft, Long aircarrier_id);

    List<Aircraft> readAll();

}