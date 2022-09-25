package com.solvd.airport.domain.flight;

import java.math.BigDecimal;

public class Direction {

    private Long id;
    private String country;
    private BigDecimal distance;

    public String toString() {
        return ("Direction: id: " + this.id + " country: " + this.country + " distance: " + this.distance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
