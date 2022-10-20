package com.solvd.airport.domain.port;

public class AirportAircarrier {

    private Long id;
    private Long airportId;
    private Long aircarrierId;

    public String toString() {
        return ("Airport-Aircarrier: id: " + this.id
                + "\n  airports: " + this.airportId
                + "\n  aircarriers: " + this.aircarrierId
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public Long getAircarrierId() {
        return aircarrierId;
    }

    public void setAircarrierId(Long aircarrierId) {
        this.aircarrierId = aircarrierId;
    }
}
