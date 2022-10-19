package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.AirstripRepository;
import com.solvd.airport.persistence.impl.AirstripMapperImpl;
import com.solvd.airport.persistence.impl.AirstripRepositoryImpl;
import com.solvd.airport.service.AirstripService;

import java.util.List;

public class AirstripServiceImpl implements AirstripService {

    private final AirstripRepository airstripRepository;

    public AirstripServiceImpl() {
//        this.airstripRepository = new AirstripMapperImpl();
        this.airstripRepository = new AirstripRepositoryImpl();
    }

    @Override
    public Airstrip create(Airstrip airstrip, Long airportId) {
        System.out.println("SERVICE create airstrip");
        airstrip.setId(null);
        airstripRepository.create(airstrip, airportId);
        return airstrip;
    }

    @Override
    public List<Airstrip> readAll() {
        System.out.println("SERVICE readAll airstrip");
        return airstripRepository.readAll();
    }

    @Override
    public Airstrip readById(Long id) {
        System.out.println("SERVICE readById airstrip");
        return airstripRepository.readById(id)
                .orElseThrow(() -> new NotFound("Airstrip with id=" + id + " not found"));
    }

    @Override
    public void update(Airstrip airstrip, Long airportId) {
        System.out.println("SERVICE update airstrip");
        airstripRepository.update(airstrip, airportId);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById airstrip");
        airstripRepository.deleteById(id);
    }
}
