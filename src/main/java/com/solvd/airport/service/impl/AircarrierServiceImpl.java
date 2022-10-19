package com.solvd.airport.service.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.port.AirportAircarrier;
import com.solvd.airport.persistence.AircarrierRepository;
import com.solvd.airport.persistence.impl.AircarrierMapperImpl;
import com.solvd.airport.service.*;

import java.util.List;

public class AircarrierServiceImpl implements AircarrierService {

    private final AircarrierRepository aircarrierRepository;
    private final PilotService pilotService;
    private final AircraftService aircraftService;
    private final FlightService flightService;

    public AircarrierServiceImpl() {
        this.aircarrierRepository = new AircarrierMapperImpl();
        this.aircraftService = new AircraftServiceImpl();
        this.flightService = new FlightServiceImpl();
        this.pilotService = new PilotServiceImpl();
    }

    @Override
    public Aircarrier create(Aircarrier aircarrier) {
        System.out.println("SERVICE create aircarrier");
        aircarrier.setId(null);
        aircarrierRepository.create(aircarrier);
        if (aircarrier.getPilots() != null) {
            aircarrier.getPilots()
                    .forEach(pilot -> {
                        if (pilot.getId() == null) {
                            pilotService.create(pilot, aircarrier.getId());
                        }
                        else {
                            pilotService.update(pilot, aircarrier.getId());
                        }
                    });
        }
        if (aircarrier.getAircrafts() != null) {
            aircarrier.getAircrafts()
                    .forEach(aircraft -> {
                        if (aircraft.getId() == null) {
                            aircraftService.create(aircraft, aircarrier.getId());
                        }
                        else {
                            aircraftService.update(aircraft, aircarrier.getId());
                        }
                    });
        }
        if (aircarrier.getFlights() != null) {
            aircarrier.getFlights()
                    .forEach(flight -> {
                        if (flight.getId() == null) {
                            flightService.create(flight, aircarrier.getId());
                        }
                        else {
                            flightService.update(flight, aircarrier.getId());
                        }
                    });
        }
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
        return aircarrierRepository.readById(id)
                .orElseThrow(() -> new NotFound("Aircarrier with id=" + id + " not found"));
    }

    @Override
    public void update(Aircarrier aircarrier) {
        System.out.println("SERVICE update aircarrier");
        aircarrierRepository.update(aircarrier);
        if (aircarrier.getPilots() != null) {
            aircarrier.getPilots()
                    .forEach(pilot -> {
                        if (pilot.getId() == null) {
                            pilotService.create(pilot, aircarrier.getId());
                        }
                        else {
                            pilotService.update(pilot, aircarrier.getId());
                        }
                    });
        }
        if (aircarrier.getAircrafts() != null) {
            aircarrier.getAircrafts()
                    .forEach(aircraft -> {
                        if (aircraft.getId() == null) {
                            aircraftService.create(aircraft, aircarrier.getId());
                        }
                        else {
                            aircraftService.update(aircraft, aircarrier.getId());
                        }
                    });
        }
        if (aircarrier.getFlights() != null) {
            aircarrier.getFlights()
                    .forEach(flight -> {
                        if (flight.getId() == null) {
                            flightService.create(flight, aircarrier.getId());
                        }
                        else {
                            flightService.update(flight, aircarrier.getId());
                        }
                    });
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById aircarrier");
        aircarrierRepository.deleteById(id);
    }
}
