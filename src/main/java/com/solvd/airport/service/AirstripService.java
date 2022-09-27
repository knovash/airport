package com.solvd.airport.service;

import com.solvd.airport.domain.port.Airstrip;

import java.util.List;

public interface AirstripService {

    Airstrip create(Airstrip airstrip, Long airportId);

    List<Airstrip> readAll();
}