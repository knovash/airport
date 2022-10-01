package com.solvd.airport.domain.carrier;

public class Pilot {

    private Long id;
    private String name;
//    private Long aircarrierId;

    public String toString() {
        return ("Pilot: id: " + this.id + " name: " + this.name);
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

//    public Long getAircarrierId() {
//        return aircarrierId;
//    }
//
//    public void setAircarrierId(Long aircarrier_id) {
//        this.aircarrierId = aircarrier_id;
//    }
}
