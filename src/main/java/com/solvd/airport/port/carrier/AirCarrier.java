package com.solvd.airport.port.carrier;

import com.solvd.airport.port.carrier.flight.Flight;

import java.util.List;

public class AirCarrier {
    private Long id;
    private String airCarrierName;
    private List<Flight> Flights;
    private List<AirCraft> airCrafts;
    private List<Pilot> pilots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirCarrierName() {
        return airCarrierName;
    }

    public void setAirCarrierName(String airCarrierName) {
        this.airCarrierName = airCarrierName;
    }

    public List<Flight> getFlights() {
        return Flights;
    }

    public void setFlights(List<Flight> flights) {
        Flights = flights;
    }

    public List<AirCraft> getAirCrafts() {
        return airCrafts;
    }

    public void setAirCrafts(List<AirCraft> airCrafts) {
        this.airCrafts = airCrafts;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
}
