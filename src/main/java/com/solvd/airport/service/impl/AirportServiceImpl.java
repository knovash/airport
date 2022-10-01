package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.impl.AirportRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AircarrierService aircarrierService;
    private final AirstripService airstripService;


    public AirportServiceImpl() {
        this.airportRepository = new AirportRepositoryImpl();
        this.aircarrierService = new AircarrierServiceImpl();
        this.airstripService = new AirstripServiceImpl();
    }



//    private Long id;
//    private String name;
//    private List<Airstrip> airstrips;
//    private List<Aircarrier> aircarriers;

    @Override
    public Airport create(Airport airport) {
        airport.setId(null);
        airportRepository.create(airport);

        if (airport.getAircarriers() != null) {
            List<Aircarrier> aircarriers = airport.getAircarriers().stream()
                    .map(aircarrier -> aircarrierService.create(aircarrier))
                    .collect(Collectors.toList());
            airport.setAircarriers(aircarriers);
        }

        if (airport.getAirstrips() != null) {
            List<Airstrip> airstrips = airport.getAirstrips().stream()
                    .map(airstrip -> airstripService.create(airstrip, airport.getId()))
                    .collect(Collectors.toList());
            airport.setAirstrips(airstrips);
        }


        return airport;
    }

    @Override
    public List<Airport> readAll() {
        return airportRepository.readAll();
    }

    @Override
    public Airport readById(Long id) {
        return null;
    }

    @Override
    public void update(Airport airport) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
