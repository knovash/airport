package com.solvd.airport.domain.carrier;

import java.time.LocalDate;

public class Aircraft {

    private Long id;
    private Integer number;
    private String model;
    private Integer seats;
    private LocalDate serviceDate;
    private Long aircarrierId;

    public String toString() {
        return ("Aircraft: id: " + this.id + " number: " + this.number + " model: " + this.model + " seats: " + this.seats + " serviceDate: " + this.serviceDate);
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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Long getAircarrierId() {
        return aircarrierId;
    }

    public void setAircarrierId(Long aircarrierId) {
        this.aircarrierId = aircarrierId;
    }
}
