package com.solvd.airport.port.carrier;

import java.time.LocalDate;

public class AirCraft {
    private Integer airCraftNumber;
    private String airCraftModel;
    private LocalDate dateOfService;

    public Integer getAirCraftNumber() {
        return airCraftNumber;
    }

    public void setAirCraftNumber(Integer airCraftNumber) {
        this.airCraftNumber = airCraftNumber;
    }
}
