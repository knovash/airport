package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.impl.AircraftMapperImpl;
import com.solvd.airport.persistence.impl.AircraftRepositoryImpl;
import com.solvd.airport.persistence.impl.PassengerRepositoryImpl;
import com.solvd.airport.service.AircraftService;

import java.util.List;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftServiceImpl() {
//        this.aircraftRepository = new AircraftMapperImpl();
        this.aircraftRepository = new AircraftRepositoryImpl();
    }

    @Override
    public Aircraft create(Aircraft aircraft, Long aircarrierId) {
        System.out.println("SERVICE create aircraft");
        aircraft.setId(null);
        aircraftRepository.create(aircraft, aircarrierId);
        return aircraft;
    }

    @Override
    public List<Aircraft> readAll() {
        System.out.println("SERVICE readAll aircrafts");
        return aircraftRepository.readAll();
    }

    @Override
    public Aircraft readById(Long id) {
        System.out.println("SERVICE readById aircraft");
        return aircraftRepository.readById(id)
                .orElseThrow(() -> new NotFound("Aircraft with id=" + id + " not found"));
    }

    @Override
    public void update(Aircraft aircraft, Long aircarrierId) {
        System.out.println("SERVICE update aircraft");
        aircraftRepository.update(aircraft, aircarrierId);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById aircraft");
        aircraftRepository.deleteById(id);
    }
}
