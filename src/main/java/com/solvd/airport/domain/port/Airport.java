package com.solvd.airport.domain.port;

import com.solvd.airport.domain.carrier.Aircarrier;

import java.util.List;

public class Airport {

    private Long id;
    private String name;
    private List<Airstrip> airstrips;
    private List<Aircarrier> aircarriers;
    private List<AirportAircarrier> airportAircarriers;

    public String toString() {
        return ("\nAIRPORT: id: " + this.id
                + " name: " + this.name
                + "\n  airstrips: " + this.airstrips
                + "\n  aircarriers: " + this.aircarriers
        );
    }

    public List<AirportAircarrier> getAirportAircarriers() {
        return airportAircarriers;
    }

    public void setAirportAircarriers(List<AirportAircarrier> airportAircarriers) {
        this.airportAircarriers = airportAircarriers;
    }

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

    public List<Airstrip> getAirstrips() {
        return airstrips;
    }

    public void setAirstrips(List<Airstrip> airstrips) {
        this.airstrips = airstrips;
    }

    public List<Aircarrier> getAircarriers() {
        return aircarriers;
    }

    public void setAircarriers(List<Aircarrier> aircarriers) {
        this.aircarriers = aircarriers;
    }
}
