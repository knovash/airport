package com.solvd.airport.port;

import com.solvd.airport.port.carrier.AirCarrier;

import java.util.List;

public class AirPort {
    private Long id;
    private String airPortName;
    private List<AirCarrier> airCarriers;
    private List<AirStrip> airStrips;
    private List<Gate> gates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirPortName() {
        return airPortName;
    }

    public void setAirPortName(String airPortName) {
        this.airPortName = airPortName;
    }

    public List<AirCarrier> getAirCarriers() {
        return airCarriers;
    }

    public void setAirCarriers(List<AirCarrier> airCarriers) {
        this.airCarriers = airCarriers;
    }

    public List<AirStrip> getAirStrips() {
        return airStrips;
    }

    public void setAirStrips(List<AirStrip> airStrips) {
        this.airStrips = airStrips;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }
}
