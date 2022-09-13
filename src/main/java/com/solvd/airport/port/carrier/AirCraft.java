package com.solvd.airport.port.carrier;

import java.time.LocalDate;

public class AirCraft {
    private Long id;
    private AirCarrier airCarrier;
    private Integer airCraftNumber;
    private String airCraftModel;
    private LocalDate dateOfService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirCarrier getAirCarrier() {
        return airCarrier;
    }

    public void setAirCarrier(AirCarrier airCarrier) {
        this.airCarrier = airCarrier;
    }

    public Integer getAirCraftNumber() {
        return airCraftNumber;
    }

    public void setAirCraftNumber(Integer airCraftNumber) {
        this.airCraftNumber = airCraftNumber;
    }

    public String getAirCraftModel() {
        return airCraftModel;
    }

    public void setAirCraftModel(String airCraftModel) {
        this.airCraftModel = airCraftModel;
    }

    public LocalDate getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(LocalDate dateOfService) {
        this.dateOfService = dateOfService;
    }
}
