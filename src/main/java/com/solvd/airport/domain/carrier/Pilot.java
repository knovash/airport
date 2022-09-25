package com.solvd.airport.domain.carrier;

import com.solvd.airport.domain.flight.Flight;

import java.util.List;

public class Pilot {

    private Long id;
    private String name;
    private Long aircarrierId;
//    private License license;
//    private List<Flight> flights;

    public String toString() {
//        return ("Pilot: id: " + this.id + " name: " + this.name + " license: " + this.license + " Flights: " + this.flights);
        return ("Pilot: id: " + this.id + " name: " + this.name + " aircarrierId: " + this.aircarrierId);
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

    public Long getAircarrierId() {
        return aircarrierId;
    }

    public void setAircarrierId(Long aircarrier_id) {
        this.aircarrierId = aircarrier_id;
    }

    //    public License getLicense() {
//        return license;
//    }
//
//    public void setLicense(License license) {
//        this.license = license;
//    }
//
//    public List<Flight> getFlights() {
//        return flights;
//    }
//
//    public void setFlights(List<Flight> flights) {
//        flights = flights;
//    }
}
