package com.solvd.airport.domain.carrier;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.port.AirportAircarrier;

import java.util.List;

public class Aircarrier {

    private Long id;
    private String name;
    private List<Flight> flights;
    private List<Aircraft> aircrafts;
    private List<Pilot> pilots;
    private List<AirportAircarrier> airportAircarriers;

    public String toString() {
        return ("\nAircarrier: id: " + this.id
                + " name: " + this.name
                + "\n  pilots: " + this.pilots
                + "\n  aircrafts: " + this.aircrafts
                + "\n  flights: " + this.flights
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

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
}
