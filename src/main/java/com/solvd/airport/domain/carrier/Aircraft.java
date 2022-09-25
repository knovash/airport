package com.solvd.airport.domain.carrier;

import java.time.LocalDate;

public class Aircraft {

    private Long id;
    private Integer number;
    private String model;
    private Long aircarrierId;

    public String toString() {
        return ("Aircraft: id: " + this.id + " number: " + this.number + " model: " + this.model + " aircarrierId: " + this.aircarrierId);
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getAircarrierId() {
        return aircarrierId;
    }

    public void setAircarrierId(Long aircarrierId) {
        this.aircarrierId = aircarrierId;
    }
}
