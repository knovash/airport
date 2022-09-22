package com.solvd.airport.persistance;

import com.solvd.airport.domain.port.Gate;

import java.util.List;

public interface GateRepository {

    void create(Gate gate);

    List<Gate> readAll();

    Gate readById(Long id);

    List<Gate> readByAirportId(Long airportId);

    void update(Gate gate);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}