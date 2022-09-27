package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistance.AircarrierRepository;
import com.solvd.airport.persistance.impl.AircarrierRepositoryImpl;
import com.solvd.airport.service.AircarrierService;
import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.FlightService;
import com.solvd.airport.service.PilotService;

import java.util.List;
import java.util.stream.Collectors;

public class AircarrierServiceImpl implements AircarrierService {

    private final AircarrierRepository aircarrierRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final FlightService flightService;

    public AircarrierServiceImpl() {
        this.aircarrierRepository = new AircarrierRepositoryImpl();
        this.pilotService = new PilotServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.flightService = new FlightServiceImpl();
    }

//    private Long id;
//    private String name;
//    private List<Flight> flights;
//    private List<Aircraft> aircrafts;
//    private List<Pilot> pilots;

    @Override
    public Aircarrier create(Aircarrier aircarrier, Long airportId) {
        aircarrier.setId(null);
        aircarrierRepository.create(aircarrier);

        if (aircarrier.getPilots() != null) {
            List<Pilot> pilots = aircarrier.getPilots().stream()
                    .map(pilot -> pilotService.create(pilot, aircarrier.getId()))
                    .collect(Collectors.toList());
            aircarrier.setPilots(pilots);
        }

        if (aircarrier.getAircrafts() != null) {
            List<Aircraft> aircrafts = aircarrier.getAircrafts().stream()
                    .map(aircraft -> aircraftService.create(aircraft, aircarrier.getId()))
                    .collect(Collectors.toList());
            aircarrier.setAircrafts(aircrafts);
        }

        if (aircarrier.getFlights() != null) {
            List<Flight> flights = aircarrier.getFlights().stream()
                    .map(flight -> flightService.create(flight, aircarrier.getId()))
                    .collect(Collectors.toList());
            aircarrier.setFlights(flights);
        }

        return aircarrier;
    }

    @Override
    public List<Aircarrier> readAll() {
        return aircarrierRepository.readAll();
    }
}