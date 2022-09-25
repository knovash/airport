package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.persistance.PilotRepository;
import com.solvd.airport.persistance.impl.PilotRepositoryImpl;
import com.solvd.airport.service.PilotService;

import java.util.List;

public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl() {
        this.pilotRepository = new PilotRepositoryImpl();
    }

    @Override
    public Pilot create(Pilot pilot, Long aircarrierId) {
        pilot.setId(null);

        pilotRepository.create(pilot, aircarrierId); // pilotRepository в persistance там sql insert зааносит информацию из полей в бд

        return pilot;
    }

    @Override
    public List<Pilot> readAll() {
        return pilotRepository.readAll();
    }
}