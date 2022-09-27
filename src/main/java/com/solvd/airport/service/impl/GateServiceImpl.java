package com.solvd.airport.service.impl;

import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.GateRepository;
import com.solvd.airport.persistance.impl.GateRepositoryImpl;
import com.solvd.airport.service.GateService;

import java.util.List;

public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    public GateServiceImpl() {
        this.gateRepository = new GateRepositoryImpl();
    }

//    private Long id;
//    private Integer number;
//    private Long airportId;

    @Override
    public Gate create(Gate gate, Long airportId) {
        gate.setId(null);
        gateRepository.create(gate, airportId);
        return gate;
    }

    @Override
    public List<Gate> readAll() {
        return gateRepository.readAll();
    }
}