package com.solvd.airport.persistance;

import com.solvd.airport.domain.port.Gate;

import java.util.List;

public interface GateRepository {

    void create(Gate gate, Long airportId);

    List<Gate> readAll();

    Gate readById(Long id);

    void update(Gate gate);

    void deleteById(Long id);
}