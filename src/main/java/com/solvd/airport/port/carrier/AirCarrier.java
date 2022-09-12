package com.solvd.airport.port.carrier;

import com.solvd.airport.port.carrier.flight.Flight;

import java.util.List;

public class AirCarrier {
    private String airCarrierName;
    private List<Flight> Flights;
    private List<AirCraft> airCrafts;
    private List<Pilot> pilots;

    public String getAirCarrierName() {
        return airCarrierName;
    }

    public void setAirCarrierName(String airCarrierName) {
        this.airCarrierName = airCarrierName;
    }
}
