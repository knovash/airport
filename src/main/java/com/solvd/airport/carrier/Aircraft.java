package com.solvd.airport.carrier;

import java.time.LocalDate;

public class Aircraft {

    private Long id;
    private Aircarrier aircarrier;
    private Integer number;
    private String model;
    private Integer seats;
    private LocalDate serviceDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aircarrier getAircarrier() {
        return aircarrier;
    }

    public void setAircarrier(Aircarrier aircarrier) {
        this.aircarrier = aircarrier;
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
}
