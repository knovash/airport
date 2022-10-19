package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.persistence.PilotRepository;
import com.solvd.airport.persistence.impl.PassengerRepositoryImpl;
import com.solvd.airport.persistence.impl.PilotMapperImpl;
import com.solvd.airport.persistence.impl.PilotRepositoryImpl;
import com.solvd.airport.service.PilotService;

import java.util.List;

public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl() {
//        this.pilotRepository = new PilotMapperImpl();
        this.pilotRepository = new PilotRepositoryImpl();
    }

    @Override
    public Pilot create(Pilot pilot, Long aircarrierId) {
        System.out.println("SERVICE create pilot");
        pilot.setId(null);
        pilotRepository.create(pilot, aircarrierId);
        return pilot;
    }

    @Override
    public List<Pilot> readAll() {
        System.out.println("SERVICE readAll pilot");
        return pilotRepository.readAll();
    }

    @Override
    public Pilot readById(Long id) {
        System.out.println("SERVICE readById pilot");
        return pilotRepository.readById(id)
                .orElseThrow(() -> new NotFound("Pilot with id=" + id + " not found"));
    }

    @Override
    public void update(Pilot pilot, Long aircarrierId) {
        System.out.println("SERVICE update pilot");
        pilotRepository.update(pilot, aircarrierId);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById pilot");
        pilotRepository.deleteById(id);
    }
}
