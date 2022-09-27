package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.AirportRepository;
import com.solvd.airport.persistance.impl.AirportRepositoryImpl;
import com.solvd.airport.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final GateService gateService;
    private final AircarrierService aircarrierService;
    private final AirstripService airstripService;


    public AirportServiceImpl(AirstripService airstripService) {
        this.airportRepository = new AirportRepositoryImpl();
        this.gateService = new GateServiceImpl();
        this.aircarrierService = new AircarrierServiceImpl();
        this.airstripService = airstripService;
    }

//    private Long id;
//    private String name;
//    private List<Airstrip> airstrips;
//    private List<Gate> gates;
//    private List<Aircarrier> aircarriers;

    @Override
    public Airport create(Airport airport) {
        airport.setId(null);
        airportRepository.create(airport);

        if (airport.getGates() != null) {
            List<Gate> gates = airport.getGates().stream()
                    .map(gate -> gateService.create(gate, airport.getId()))
                    .collect(Collectors.toList());
            airport.setGates(gates);
        }

        if (airport.getAircarriers() != null) {
            List<Aircarrier> aircarriers = airport.getAircarriers().stream()
                    .map(aircarrier -> aircarrierService.create(aircarrier, airport.getId()))
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
}