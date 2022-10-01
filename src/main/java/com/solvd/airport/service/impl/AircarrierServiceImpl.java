package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.persistence.AircarrierRepository;
import com.solvd.airport.persistence.impl.AircarrierMapperImpl;
import com.solvd.airport.service.AircarrierService;

import java.util.List;

public class AircarrierServiceImpl implements AircarrierService {

    private final AircarrierRepository aircarrierRepository;

    public AircarrierServiceImpl() {
//        this.aircarrierRepository = new AircarrierRepositoryImpl();
        this.aircarrierRepository = new AircarrierMapperImpl();
    }

    @Override
    public Aircarrier create(Aircarrier aircarrier) {
        System.out.println("SERVICE create aircarrier");
        aircarrier.setId(null);
        aircarrierRepository.create(aircarrier);
        return aircarrier;
    }

    @Override
    public List<Aircarrier> readAll() {
        System.out.println("SERVICE readAll aircarriers");
        return aircarrierRepository.readAll();
    }

    @Override
    public Aircarrier readById(Long id) {
        System.out.println("SERVICE readById aircarrier");
        return aircarrierRepository.readById(id);
    }

    @Override
    public void update(Aircarrier aircarrier) {
        System.out.println("SERVICE update aircarrier");
        aircarrierRepository.update(aircarrier);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById aircarrier");
        aircarrierRepository.deleteById(id);
    }
}
