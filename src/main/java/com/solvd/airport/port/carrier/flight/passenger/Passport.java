package com.solvd.airport.port.carrier.flight.passenger;

public class Passport {
    private Long id;
    private Integer passport_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(Integer passport_number) {
        this.passport_number = passport_number;
    }
}
