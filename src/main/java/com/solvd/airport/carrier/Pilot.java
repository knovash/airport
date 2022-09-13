package com.solvd.airport.carrier;

import com.solvd.airport.PilotFlight;

import java.util.List;

public class Pilot {

    private Long id;
    private String name;
    private PilotLicense pilotLicense;
    private List<PilotFlight> pilotFlights;

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

    public List<PilotFlight> getPilotFlights() {
        return pilotFlights;
    }

    public void setPilotFlights(List<PilotFlight> pilotFlights) {
        this.pilotFlights = pilotFlights;
    }
}
