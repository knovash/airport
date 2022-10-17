package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.port.AirportAircarrier;
import com.solvd.airport.persistence.AirportAircarrierRepository;
import com.solvd.airport.persistence.impl.AirportAircarrierMapperImpl;
import com.solvd.airport.service.AirportAircarrierService;

import java.util.List;

public class AirportAircarrierServiceImpl implements AirportAircarrierService {

    private final AirportAircarrierRepository airportAircarrierRepository;

    public AirportAircarrierServiceImpl() {
        this.airportAircarrierRepository = new AirportAircarrierMapperImpl();
    }

    @Override
    public AirportAircarrier create(AirportAircarrier airportAircarrier) {
        System.out.println("SERVICE create airportAircarrier.");
        airportAircarrier.setId(null);
        airportAircarrierRepository.create(airportAircarrier);
        return airportAircarrier;
    }

    @Override
    public List<AirportAircarrier> readAll() {
        System.out.println("SERVICE readAll airportAircarriers");
        return airportAircarrierRepository.readAll();
    }

    @Override
    public AirportAircarrier readById(Long id) {
        System.out.println("SERVICE readById airportAircarrier");
        return airportAircarrierRepository.readById(id)
                .orElseThrow(() -> new NotFound("AirportAircarrier with id=" + id + " not found"));
    }

    @Override
    public void update(AirportAircarrier airportAircarrier) {
        System.out.println("SERVICE update airportAircarrier");
        airportAircarrierRepository.update(airportAircarrier);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById airportAircarrier");
        airportAircarrierRepository.deleteById(id);
    }
}
