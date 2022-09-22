package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;

import java.util.List;

public interface AircraftRepository {

    void create(Aircraft aircraft);

    List<Aircraft> readAll();

    Aircraft readById(Long id);

    List<Aircraft> readByAircarrierId(Long aircarrierId);

    List<Aircraft> readByAircarriertId(Long aircarrierId);

    void update(Aircraft aircraft);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}