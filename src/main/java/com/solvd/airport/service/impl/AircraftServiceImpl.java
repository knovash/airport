package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistance.AircraftRepository;
import com.solvd.airport.persistance.impl.AircraftRepositoryImpl;
import com.solvd.airport.service.AircraftService;

import java.util.List;

public class AircraftServiceImpl implements AircraftService {

    private AircraftRepository aircraftRepository = new AircraftRepositoryImpl();

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftRepositoryImpl();

    }

    @Override
    public Aircraft create(Aircraft aircraft, Long aircarrierId) {
        aircraft.setId(null);
        aircraftRepository.create(aircraft); // aircraftRepository в persistance там sql insert зааносит информацию из полей в бд
        return aircraft;
    }

    @Override
    public List<Aircraft> readAll() {
        return null;
    }
}
