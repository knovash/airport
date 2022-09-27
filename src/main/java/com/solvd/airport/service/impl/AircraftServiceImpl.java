package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistance.AircraftRepository;
import com.solvd.airport.persistance.impl.AircraftRepositoryImpl;
import com.solvd.airport.service.AircraftService;

import java.util.List;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftRepositoryImpl();
    }

//    private Long id;
//    private Integer number;
//    private String model;
//    private Long aircarrierId;

    @Override
    public Aircraft create(Aircraft aircraft, Long aircarrierId) {
        aircraft.setId(null);
        aircraftRepository.create(aircraft, aircarrierId);
        return aircraft;
    }

    @Override
    public List<Aircraft> readAll() {
        return aircraftRepository.readAll();
    }
}