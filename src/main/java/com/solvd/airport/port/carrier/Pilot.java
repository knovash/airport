package com.solvd.airport.port.carrier;

import com.solvd.airport.port.carrier.flight.Flight;

public class Pilot {
    private Long id;
    private String name;
    private PilotLicense pilotLicense;
    private Flight flight;
    private AirCarrier airCarrier;

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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public AirCarrier getAirCarrier() {
        return airCarrier;
    }

    public void setAirCarrier(AirCarrier airCarrier) {
        this.airCarrier = airCarrier;
    }
}
