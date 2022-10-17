package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.impl.AirportMapperImpl;
import com.solvd.airport.service.*;

import java.util.List;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirstripService airstripService;
    private final AircarrierService aircarrierService;

    public AirportServiceImpl() {
//        this.airportRepository = new AirportRepositoryImpl();
        this.airportRepository = new AirportMapperImpl();
        this.airstripService = new AirstripServiceImpl();
        this.aircarrierService = new AircarrierServiceImpl();
    }

    @Override
    public Airport create(Airport airport) {
        System.out.println("SERVICE create airport");
        airport.setId(null);
        airportRepository.create(airport);

        if (airport.getAirstrips() != null) {
            airport.getAirstrips().stream()
                    .forEach(airstrip -> {
                        if (airstrip.getId() == null) {
                            airstripService.create(airstrip, airport.getId());
                        }
                        else {
                            airstripService.update(airstrip, airport.getId());
                        }
                    });
        }

        if (airport.getAircarriers() != null) {
            airport.getAircarriers().stream()
                    .forEach(aircarrier -> {
                        if (aircarrier.getId() == null) {
                            aircarrierService.create(aircarrier);
                        }
                        else {
                            aircarrierService.update(aircarrier);
                        }
                    });
        }

        return airport;
    }

    @Override
    public List<Airport> readAll() {
        System.out.println("SERVICE readAll airports");
        return airportRepository.readAll();
    }

    @Override
    public Airport readById(Long id) {
        System.out.println("SERVICE readById airport");
        return airportRepository.readById(id)
                .orElseThrow(() -> new NotFound("Airport with id=" + id + " not found"));
    }

    @Override
    public void update(Airport airport) {
        System.out.println("SERVICE update airport");
        airportRepository.update(airport);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById airport");
        airportRepository.deleteById(id);
    }
}
