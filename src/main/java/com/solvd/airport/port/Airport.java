package com.solvd.airport.port;

import java.util.List;

public class Airport {

    private Long id;
    private String name;
    private List<Airstrip> airstrips;
    private List<Gate> gates;

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

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }
}
