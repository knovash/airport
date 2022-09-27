package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;

import java.util.List;

public interface AircraftRepository {

    void create(Aircraft aircraft, Long aircarrier_id);

    List<Aircraft> readAll();

    Aircraft readById(Long id);

    void update(Aircraft aircraft);

    void deleteById(Long id);
}