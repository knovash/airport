package com.solvd.airport.domain.carrier;

import com.solvd.airport.domain.flight.Flight;

import java.util.List;

public class Pilot {

    private Long id;
    private String name;
    private PilotLicense pilotLicense;
    private List<Flight> Flights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PilotLicense getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(PilotLicense pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    public List<Flight> getFlights() {
        return Flights;
    }

    public void setFlights(List<Flight> flights) {
        Flights = flights;
    }
}
