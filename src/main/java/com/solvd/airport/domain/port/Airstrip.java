package com.solvd.airport.domain.port;

public class Airstrip {

    private Long id;
    private Integer number;
    private Long airportId;

    public String toString() {
        return ("Airstrip: id: " + this.id + " number: " + this.number + " airportId: " + this.airportId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }
}
