package com.solvd.airport.port.carrier.flight;

import java.math.BigDecimal;

public class Direction {
    private String countryName;
    private BigDecimal distance;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
