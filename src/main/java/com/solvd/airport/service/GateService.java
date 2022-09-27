package com.solvd.airport.service;

import com.solvd.airport.domain.port.Gate;

import java.util.List;

public interface GateService {

    Gate create(Gate gate, Long airportId);

    List<Gate> readAll();
}